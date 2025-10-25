package com.mycompany.emsclothing.web;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Object u = req.getSession().getAttribute("user");
    if (u == null || !"ADMIN".equals(((com.mycompany.emsclothing.model.User)u).getRole())) {
      resp.sendRedirect(req.getContextPath()+"/login"); return;
    }
    req.getRequestDispatcher("/WEB-INF/views/admin_dashboard.jsp").forward(req, resp);
  }
}
