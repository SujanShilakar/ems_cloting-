package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.dao.ProductDAO;
import com.mycompany.emsclothing.model.Product;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;

@WebServlet("/product")
public class ProductDetailServlet extends HttpServlet {
  private final ProductDAO dao = new ProductDAO();
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    long id = Long.parseLong(req.getParameter("id"));
    Product p = dao.find(id);
    if (p == null){ resp.sendError(404); return; }
    req.setAttribute("p", p);
    req.getRequestDispatcher("/WEB-INF/views/product_detail.jsp").forward(req, resp);
  }
}
