package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.dao.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
  private final ProductDAO dao = new ProductDAO();
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("products", dao.all());
    req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
  }
}
