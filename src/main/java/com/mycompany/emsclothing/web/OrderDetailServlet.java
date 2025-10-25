package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.model.Order;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/orders/*")
public class OrderDetailServlet extends HttpServlet {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // URL: /orders/{id}
    String[] parts = req.getPathInfo() == null ? new String[0] : req.getPathInfo().split("/");
    if (parts.length < 2) { resp.sendError(404); return; }
    long id = Long.parseLong(parts[1]);

    try (EntityManager em = emf.createEntityManager()) {
      // Eager load items and products in one go
      Order o = em.createQuery(
        "SELECT o FROM Order o " +
        "LEFT JOIN FETCH o.items i " +
        "LEFT JOIN FETCH i.product " +
        "WHERE o.id = :id", Order.class)
        .setParameter("id", id)
        .getSingleResult();
      req.setAttribute("o", o);
    }
    req.getRequestDispatcher("/WEB-INF/views/order_detail.jsp").forward(req, resp);
  }
}
