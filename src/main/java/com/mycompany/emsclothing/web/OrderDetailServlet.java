package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.JPAUtil;
import com.mycompany.emsclothing.model.Order;
import com.mycompany.emsclothing.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;
import jakarta.persistence.*;

@WebServlet("/orders/*")
public class OrderDetailServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User u = (User) req.getSession().getAttribute("user");
    if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
    String path = req.getPathInfo(); // like /123
    Long id = Long.valueOf(path.substring(1));
    EntityManager em = JPAUtil.em();
    Order o = em.find(Order.class, id);
    em.close();
    if (o == null || !o.getUser().getId().equals(u.getId())) {
      resp.sendError(404); return;
    }
    req.setAttribute("order", o);
    req.getRequestDispatcher("/WEB-INF/views/order_detail.jsp").forward(req, resp);
  }
}
