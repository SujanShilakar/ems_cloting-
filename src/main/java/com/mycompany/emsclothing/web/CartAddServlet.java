package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.ProductDAO;
import com.mycompany.emsclothing.model.CartItem;
import com.mycompany.emsclothing.model.Product;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
    private final ProductDAO dao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("productId"));
        int qty = Integer.parseInt(req.getParameter("qty"));

        Product p = dao.find(productId);
        if (p == null || qty < 1) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        // Added stock check
        if (p.getStock() < qty) {
            resp.sendRedirect(req.getContextPath() + "/product?id=" + productId);
            return;
        }

        HttpSession session = req.getSession(true);
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        CartItem existing = cart.stream()
                .filter(ci -> ci.getProductId().equals(productId))
                .findFirst().orElse(null);

        if (existing != null) {
            existing.setQty(existing.getQty() + qty);
        } else {
            CartItem ci = new CartItem();
            ci.setProductId(productId);
            ci.setName(p.getName());
            ci.setUnitPrice(p.getPrice());
            ci.setQty(qty);
            cart.add(ci);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
