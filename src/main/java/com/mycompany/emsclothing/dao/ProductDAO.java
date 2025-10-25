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
  try (EntityManager em = JPAUtil.em()){
    em.getTransaction().begin();
    Long refs = em.createQuery(
      "SELECT COUNT(oi) FROM OrderItem oi WHERE oi.product.id = :id", Long.class)
      .setParameter("id", id)
      .getSingleResult();

    if (refs != 0){
      em.getTransaction().rollback();
      throw new IllegalStateException("Cannot delete. Product has orders.");
    }

    Product p = em.find(Product.class, id);
    if (p != null) em.remove(p);
    em.getTransaction().commit();
  }
}


  // --- Added helpers ---

  public List<Product> search(String q, Integer categoryId, String sort){
    try (EntityManager em = emf.createEntityManager()){
      String jpql = "SELECT p FROM Product p WHERE 1=1";
      if (q != null && !q.isBlank()) jpql += " AND LOWER(p.name) LIKE :q";
      if (categoryId != null) jpql += " AND p.category.id = :cid";
      if ("price_asc".equals(sort)) jpql += " ORDER BY p.price ASC";
      else if ("price_desc".equals(sort)) jpql += " ORDER BY p.price DESC";
      else jpql += " ORDER BY p.id DESC";

      TypedQuery<Product> qy = em.createQuery(jpql, Product.class);
      if (q != null && !q.isBlank()) qy.setParameter("q", "%" + q.toLowerCase() + "%");
      if (categoryId != null) qy.setParameter("cid", categoryId);
      return qy.getResultList();
    }
  }

  public void decrementStock(long productId, int qty){
    try (EntityManager em = emf.createEntityManager()){
      em.getTransaction().begin();
      Product p = em.find(Product.class, productId);
      if (p == null){ em.getTransaction().rollback(); throw new EntityNotFoundException("Product not found"); }
      if (p.getStock() < qty){ em.getTransaction().rollback(); throw new IllegalStateException("Out of stock"); }
      p.setStock(p.getStock() - qty);
      em.getTransaction().commit();
    }
  }
}
