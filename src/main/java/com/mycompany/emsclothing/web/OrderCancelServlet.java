package com.mycompany.emsclothing.web;

import com.mycompany.emsclothing.dao.OrderDAO;
import com.mycompany.emsclothing.model.Order;
import com.mycompany.emsclothing.model.User;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/orders/cancel")
public class OrderCancelServlet extends HttpServlet {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        long id = Long.parseLong(req.getParameter("id"));

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order o = em.find(Order.class, id);

        if (o != null && o.getUser().getId().equals(u.getId())) {
            em.remove(o); // delete order
            em.getTransaction().commit();
            resp.sendRedirect(req.getContextPath() + "/orders?msg=cancelled");
        } else {
            em.getTransaction().rollback();
            resp.sendRedirect(req.getContextPath() + "/orders?err=notfound");
        }

        em.close();
    }
}
