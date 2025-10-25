package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.model.User;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User u = (User) req.getSession().getAttribute("user");
    if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
    req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
  }

  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    User sessionUser = (User) req.getSession().getAttribute("user");
    if (sessionUser == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

    String fullName = req.getParameter("fullName");
    String phone    = req.getParameter("phone");
    String address  = req.getParameter("address");

    // simple required validation
    if (fullName==null || fullName.isBlank() ||
        phone==null || phone.isBlank() ||
        address==null || address.isBlank()) {
      req.setAttribute("error","All fields are required.");
      req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
      return;
    }

    try (EntityManager em = emf.createEntityManager()) {
      em.getTransaction().begin();
      User u = em.find(User.class, sessionUser.getId());
      u.setFullName(fullName.trim());
      u.setPhone(phone.trim());
      u.setAddress(address.trim());
      em.getTransaction().commit();

      // update session copy so header etc. reflect changes
      sessionUser.setFullName(u.getFullName());
      sessionUser.setPhone(u.getPhone());
      sessionUser.setAddress(u.getAddress());
    }

    resp.sendRedirect(req.getContextPath()+"/profile?ok=1");
  }
}
