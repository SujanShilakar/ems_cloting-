package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.CategoryDAO;
import com.mycompany.emsclothing.dao.ProductDAO;
import com.mycompany.emsclothing.model.Product;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@MultipartConfig(maxFileSize = 5_000_000) // 5 MB
@WebServlet({"/admin/products/new","/admin/products/edit","/admin/products/delete"})
public class ProductEditDeleteServlet extends HttpServlet {
    private final ProductDAO dao = new ProductDAO();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

    // Save uploaded image to /uploads and return public URL
    private String saveUploaded(HttpServletRequest req, String field) throws IOException, ServletException {
        Part part = req.getPart(field);
        if (part == null || part.getSize() == 0) return null;

        String name = part.getSubmittedFileName();
        String ext = "";
        int dot = name.lastIndexOf('.');
        if (dot >= 0) ext = name.substring(dot).toLowerCase();

        String uploadDir = getServletContext().getRealPath("/uploads");
        Files.createDirectories(Path.of(uploadDir));

        String filename = UUID.randomUUID().toString().replace("-", "") + ext;
        File target = new File(uploadDir, filename);
        part.write(target.getAbsolutePath());

        // URL for <img src>
        return req.getContextPath() + "/uploads/" + filename;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();

        if ("/admin/products/new".equals(path)) {
            req.setAttribute("editing", false);
            req.setAttribute("p", new Product());
            req.setAttribute("categories", new CategoryDAO().all());
            req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
            return;
        }

        if ("/admin/products/edit".equals(path)) {
            long id = Long.parseLong(req.getParameter("id"));
            Product p = dao.find(id);
            if (p == null) { resp.sendError(404); return; }
            req.setAttribute("editing", true);
            req.setAttribute("p", p);
            req.setAttribute("categories", new CategoryDAO().all());
            req.getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String path = req.getServletPath();

        if ("/admin/products/delete".equals(path)) {
            try {
                dao.delete(Long.parseLong(req.getParameter("id")));
            } catch (IllegalStateException ex) {
                resp.sendRedirect(req.getContextPath()+"/products?err=product_in_orders");
                return;
            }
            resp.sendRedirect(req.getContextPath()+"/products");
            return;
        }

        // Common fields
        String name  = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock    = Integer.parseInt(req.getParameter("stock"));

        String uploadedUrl = saveUploaded(req, "imageFile"); // null if none

        if ("/admin/products/new".equals(path)) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Product p = new Product();
            p.setName(name);
            p.setPrice(price);
            p.setStock(stock);
            if (uploadedUrl != null) p.setImageUrl(uploadedUrl);

            String cid = req.getParameter("categoryId");
            if (cid != null && !cid.isBlank())
                p.setCategory(new CategoryDAO().find(Integer.valueOf(cid)));

            em.persist(p);
            em.getTransaction().commit();
            em.close();
            resp.sendRedirect(req.getContextPath()+"/products");
            return;
        }

        if ("/admin/products/edit".equals(path)) {
            long id = Long.parseLong(req.getParameter("id"));
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Product p = em.find(Product.class, id);
            if (p == null) { em.getTransaction().rollback(); em.close(); resp.sendError(404); return; }

            p.setName(name);
            p.setPrice(price);
            p.setStock(stock);
            if (uploadedUrl != null) p.setImageUrl(uploadedUrl); // keep old if none uploaded

            String cid = req.getParameter("categoryId");
            if (cid != null && !cid.isBlank())
                p.setCategory(new CategoryDAO().find(Integer.valueOf(cid)));
            else
                p.setCategory(null);

            em.getTransaction().commit();
            em.close();
            resp.sendRedirect(req.getContextPath()+"/products");
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
