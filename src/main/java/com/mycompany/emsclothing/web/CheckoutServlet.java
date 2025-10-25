package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        var u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        var cart = (List<CartItem>) req.getSession().getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        Long orderId;

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Order order = new Order();
            order.setUser(u);
            order.setStatus("PAID"); // ✅ Automatically mark as PAID
            double total = 0;

            em.persist(order);

            for (CartItem ci : cart) {
                Product p = em.find(Product.class, ci.getProductId());
                if (p.getStock() < ci.getQty()) {
                    em.getTransaction().rollback();
                    resp.sendRedirect(req.getContextPath() + "/cart");
                    return;
                }

                OrderItem oi = new OrderItem();
                oi.setOrder(order);
                oi.setProduct(p);
                oi.setPrice(ci.getUnitPrice());
                oi.setQty(ci.getQty());
                total += ci.getLineTotal();
                em.persist(oi);

                // reduce stock
                p.setStock(p.getStock() - ci.getQty());
            }

            order.setTotal(total);

            em.getTransaction().commit();
            orderId = order.getId();
        }

        // clear cart and redirect
        req.getSession().removeAttribute("cart");
        resp.sendRedirect(req.getContextPath() + "/orders/" + orderId + "?placed=1");
    }
}
