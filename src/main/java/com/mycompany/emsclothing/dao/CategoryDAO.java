package com.mycompany.emsclothing.dao;
import com.mycompany.emsclothing.model.Category;
import jakarta.persistence.*;
import java.util.List;

public class CategoryDAO {
  public List<Category> all(){
    try (EntityManager em = JPAUtil.em()){
      return em.createQuery("SELECT c FROM Category c ORDER BY c.name", Category.class).getResultList();
    }
  }
  public Category find(Integer id){
    try (EntityManager em = JPAUtil.em()){ return em.find(Category.class, id); }
  }

    public Object list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
