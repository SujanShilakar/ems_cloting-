package com.mycompany.emsclothing.dao;

import com.mycompany.emsclothing.model.User;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

public class DataSeeder {

    public static void seedAdmin() {
        EntityManager em = JPAUtil.em();
        try {
            long count = em.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.role = 'ADMIN'", Long.class
            ).getSingleResult();

            if (count == 0) {
                em.getTransaction().begin();
                User admin = new User();
                admin.setEmail("admin@ems.com");
                admin.setPasswordHash(BCrypt.hashpw("password", BCrypt.gensalt()));
                admin.setRole("ADMIN");
                em.persist(admin);
                em.getTransaction().commit();
                System.out.println("✅ Admin seeded: admin@ems.com / password");
            } else {
                System.out.println("ℹ️ Admin already exists, skipping seeding.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
