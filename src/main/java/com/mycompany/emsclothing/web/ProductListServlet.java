package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.dao.ProductDAO;
import com.mycompany.emsclothing.dao.CategoryDAO;
import com.mycompany.emsclothing.dao.DataSeeder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
  private final ProductDAO dao = new ProductDAO();

  @Override 
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
//      DataSeeder.seedAdmin();
    String q = req.getParameter("q");
    String cat = req.getParameter("categoryId");
    String sort = req.getParameter("sort");
    Integer cid = (cat == null || cat.isBlank()) ? null : Integer.valueOf(cat);

    var cdao = new CategoryDAO();
    req.setAttribute("categories", cdao.all());
    req.setAttribute("products", dao.search(q, cid, sort));
    req.setAttribute("q", q);
    req.setAttribute("categoryId", cid);
    req.setAttribute("sort", sort);

    req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
  }
}
