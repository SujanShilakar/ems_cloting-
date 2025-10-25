package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.ProductDAO;
import com.mycompany.emsclothing.dao.CategoryDAO;
import com.mycompany.emsclothing.model.Product;
import com.mycompany.emsclothing.model.Category;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/admin/products/create")
public class ProductCreateServlet extends HttpServlet {
  private final ProductDAO dao = new ProductDAO();
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
  req.setAttribute("editing", false);
  req.setAttribute("p", new com.mycompany.emsclothing.model.Product());
  req.setAttribute("categories", new com.mycompany.emsclothing.dao.CategoryDAO().all());
  req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
}

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String name = req.getParameter("name");
    String price = req.getParameter("price");
    if (name == null || name.isBlank() || price == null) {
      req.setAttribute("error", "Name and price required");
      req.setAttribute("categories", new CategoryDAO().all());
      req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
      return;
    }

    // Build Product with extra fields
    Product p = new Product();
    p.setName(name.trim());
    p.setPrice(Double.parseDouble(price));
    p.setImageUrl(req.getParameter("imageUrl"));
    String stockStr = req.getParameter("stock");
    p.setStock((stockStr == null || stockStr.isBlank()) ? 0 : Integer.parseInt(stockStr));

    String cid = req.getParameter("categoryId");
    if (cid != null && !cid.isBlank())
      p.setCategory(new CategoryDAO().find(Integer.valueOf(cid)));
    else
      p.setCategory(null);

    // Persist product
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(p);
    em.getTransaction().commit();
    em.close();

    resp.sendRedirect(req.getContextPath() + "/products");
  }
}
