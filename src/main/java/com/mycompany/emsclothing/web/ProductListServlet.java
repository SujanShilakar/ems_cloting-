package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.model.Product;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("emsPU");
    }

    @Override
    public void destroy() {
        if (emf != null) emf.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Product> list = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        em.close();
        req.setAttribute("products", list);
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}
