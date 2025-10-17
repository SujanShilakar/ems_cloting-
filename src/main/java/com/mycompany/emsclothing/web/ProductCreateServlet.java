package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.dao.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;

@WebServlet("/admin/products/create")
public class ProductCreateServlet extends HttpServlet {
  private final ProductDAO dao = new ProductDAO();
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String name = req.getParameter("name");
    String price = req.getParameter("price");
    if (name == null || name.isBlank() || price == null) {
      req.setAttribute("error","Name and price required"); 
      req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp); return;
    }
    dao.create(name.trim(), Double.parseDouble(price));
    resp.sendRedirect(req.getContextPath()+"/products");
  }
}
