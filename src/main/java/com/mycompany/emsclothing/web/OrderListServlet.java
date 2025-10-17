package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.JPAUtil;
import com.mycompany.emsclothing.model.Order;
import com.mycompany.emsclothing.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;
import jakarta.persistence.*;
import java.util.List;

@WebServlet("/orders")
public class OrderListServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User u = (User) req.getSession().getAttribute("user");
    if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
    EntityManager em = JPAUtil.em();
    List<Order> orders = em.createQuery(
      "SELECT o FROM Order o WHERE o.user.id = :uid ORDER BY o.id DESC", Order.class)
      .setParameter("uid", u.getId()).getResultList();
    em.close();
    req.setAttribute("orders", orders);
    req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
  }
}
