package com.mycompany.emsclothing.web;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;


@WebServlet("/admin/sales")
public class AdminSalesServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    var u = (com.mycompany.emsclothing.model.User) req.getSession().getAttribute("user");
    if (u == null || !"ADMIN".equals(u.getRole())) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
    var rows = new com.mycompany.emsclothing.dao.ReportDAO().topSold();
    req.setAttribute("rows", rows);
    req.getRequestDispatcher("/WEB-INF/views/admin_sales.jsp").forward(req, resp);
  }
}
