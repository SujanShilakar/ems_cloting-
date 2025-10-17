package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.dao.ProductDAO;
import com.mycompany.emsclothing.model.Product;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;

@WebServlet({"/admin/products/new","/admin/products/edit","/admin/products/delete"})
public class ProductEditDeleteServlet extends HttpServlet {
  private final ProductDAO dao = new ProductDAO();

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    if ("/admin/products/new".equals(path)) {
      req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
    } else if ("/admin/products/edit".equals(path)) {
      long id = Long.parseLong(req.getParameter("id"));
      Product p = dao.find(id);
      req.setAttribute("product", p);
      req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
    }
  }

  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getServletPath();
    if ("/admin/products/delete".equals(path)) {
      dao.delete(Long.parseLong(req.getParameter("id")));
    } else { // update
      dao.update(Long.parseLong(req.getParameter("id")), req.getParameter("name"),
                 Double.parseDouble(req.getParameter("price")));
    }
    resp.sendRedirect(req.getContextPath()+"/products");
  }
}
