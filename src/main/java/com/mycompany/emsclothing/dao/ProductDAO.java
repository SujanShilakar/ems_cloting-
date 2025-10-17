package com.mycompany.emsclothing.dao;
import com.mycompany.emsclothing.model.Product;
import jakarta.persistence.*;
import java.util.List;

public class ProductDAO {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPU");

  public List<Product> all(){
    try (EntityManager em = emf.createEntityManager()){
      return em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class).getResultList();
    }
  }
  public Product find(long id){
    try (EntityManager em = emf.createEntityManager()){ return em.find(Product.class, id); }
  }
  public void create(String name, double price){
    try (EntityManager em = emf.createEntityManager()){
      em.getTransaction().begin();
      Product p = new Product(); p.setName(name); p.setPrice(price);
      em.persist(p);
      em.getTransaction().commit();
    }
  }
  public void update(long id, String name, double price){
    try (EntityManager em = emf.createEntityManager()){
      em.getTransaction().begin();
      Product p = em.find(Product.class, id);
      p.setName(name); p.setPrice(price);
      em.getTransaction().commit();
    }
  }
  public void delete(long id){
    try (EntityManager em = emf.createEntityManager()){
      em.getTransaction().begin();
      Product p = em.find(Product.class, id);
      if (p != null) em.remove(p);
      em.getTransaction().commit();
    }
  }
}
