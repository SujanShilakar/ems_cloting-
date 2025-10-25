package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.UserDAO;
import com.mycompany.emsclothing.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private final UserDAO dao = new UserDAO();

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
  }

  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String email = req.getParameter("email").trim();
    String pass  = req.getParameter("password");

    User u = dao.findByEmail(email);
    boolean ok = (u != null) && BCrypt.checkpw(pass, u.getPasswordHash());

    if (!ok) {
      req.setAttribute("error", "Invalid credentials");
      req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
      return;
    }

    req.getSession(true).setAttribute("user", u);
    resp.sendRedirect(req.getContextPath()+"/");
  }
}
