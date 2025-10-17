package com.mycompany.emsclothing.dao;
import com.mycompany.emsclothing.model.User;
import jakarta.persistence.*;
import com.mycompany.emsclothing.dao.JPAUtil;




public class UserDAO {
  public User findByEmail(String email){
    try(EntityManager em=JPAUtil.em()){
      return em.createQuery("SELECT u FROM User u WHERE u.email=:e", User.class)
               .setParameter("e", email).getResultStream().findFirst().orElse(null);
    }
  }
  public void create(String email, String passwordHash, String role){
    try(EntityManager em=JPAUtil.em()){
      em.getTransaction().begin();
      var u=new User(); u.setEmail(email); u.setPasswordHash(passwordHash); u.setRole(role);
      em.persist(u); em.getTransaction().commit();
    }
  }
}
