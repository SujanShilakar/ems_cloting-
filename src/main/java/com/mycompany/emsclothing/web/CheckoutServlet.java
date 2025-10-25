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

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
  }

  @SuppressWarnings("unchecked")
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var u = (com.mycompany.emsclothing.model.User) req.getSession().getAttribute("user");
    if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

    var cart = (java.util.List<com.mycompany.emsclothing.model.CartItem>) req.getSession().getAttribute("cart");
    if (cart == null || cart.isEmpty()) { resp.sendRedirect(req.getContextPath()+"/cart"); return; }

    Long orderId;
    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();

      var order = new com.mycompany.emsclothing.model.Order();
      order.setUser(u);
      double total = 0;
      em.persist(order);

      for (var ci : cart) {
        var p = em.find(com.mycompany.emsclothing.model.Product.class, ci.getProductId());
        if (p.getStock() < ci.getQty()) { em.getTransaction().rollback(); resp.sendRedirect(req.getContextPath()+"/cart"); return; }

        var oi = new com.mycompany.emsclothing.model.OrderItem();
        oi.setOrder(order);
        oi.setProduct(p);
        oi.setPrice(ci.getUnitPrice());
        oi.setQty(ci.getQty());
        total += ci.getLineTotal();
        em.persist(oi);

        p.setStock(p.getStock() - ci.getQty());
      }

      order.setTotal(total);
      em.getTransaction().commit();
      orderId = order.getId();
    }

    req.getSession().removeAttribute("cart");
    resp.sendRedirect(req.getContextPath()+"/orders/" + orderId + "?placed=1");
  }
}
