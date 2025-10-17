package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.model.CartItem;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/cart", "/cart/update", "/cart/remove", "/cart/clear"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        String path = req.getServletPath();
        if ("/cart/update".equals(path)) {
            long productId = Long.parseLong(req.getParameter("productId"));
            int qty = Integer.parseInt(req.getParameter("qty"));
            cart.stream().filter(ci -> ci.getProductId().equals(productId))
                .findFirst().ifPresent(ci -> ci.setQty(Math.max(1, qty)));
        } else if ("/cart/remove".equals(path)) {
            long productId = Long.parseLong(req.getParameter("productId"));
            cart.removeIf(ci -> ci.getProductId().equals(productId));
        } else if ("/cart/clear".equals(path)) {
            cart.clear();
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
