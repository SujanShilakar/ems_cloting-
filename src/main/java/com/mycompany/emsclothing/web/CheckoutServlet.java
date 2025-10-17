package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.OrderDAO;
import com.mycompany.emsclothing.model.*;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");
  private final OrderDAO orderDAO = new OrderDAO();

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
  }

  @SuppressWarnings("unchecked")
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User u = (User) req.getSession().getAttribute("user");
    if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

    List<CartItem> cart = (List<CartItem>) req.getSession().getAttribute("cart");
    if (cart == null || cart.isEmpty()) { resp.sendRedirect(req.getContextPath()+"/cart"); return; }

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Order order = new Order();
    order.setUser(u);
    double total = 0;
    em.persist(order);

    for (CartItem ci : cart) {
      OrderItem oi = new OrderItem();
      oi.setOrder(order);
      oi.setProduct(em.getReference(Product.class, ci.getProductId()));
      oi.setPrice(ci.getUnitPrice());
      oi.setQty(ci.getQty());
      total += ci.getLineTotal();
      em.persist(oi);
      order.getItems().add(oi);
    }
    order.setTotal(total);

    em.getTransaction().commit();
    em.close();

    req.getSession().removeAttribute("cart");
    resp.sendRedirect(req.getContextPath()+"/orders/" + order.getId());
  }
}
