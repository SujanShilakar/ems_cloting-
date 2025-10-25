package com.mycompany.emsclothing.dao;

import com.mycompany.emsclothing.model.Order;
import jakarta.persistence.EntityManager;
import java.util.List;

public class OrderDAO {

    public void save(Order order) {
        EntityManager em = JPAUtil.em();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    public List<Order> findAll() {
        EntityManager em = JPAUtil.em();
        List<Order> orders = em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
        em.close();
        return orders;
    }

    // ✅ Find order by ID
    public Order find(long id) {
        EntityManager em = JPAUtil.em();
        Order o = em.find(Order.class, id);
        em.close();
        return o;
    }

    // ✅ Delete order by ID
    public void delete(long id) {
        EntityManager em = JPAUtil.em();
        em.getTransaction().begin();
        Order o = em.find(Order.class, id);
        if (o != null) {
            em.remove(o);
        }
        em.getTransaction().commit();
        em.close();
    }
}
