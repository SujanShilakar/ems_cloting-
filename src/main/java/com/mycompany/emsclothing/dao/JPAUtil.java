package com.mycompany.emsclothing.dao;
import jakarta.persistence.*;

public class JPAUtil {
  private static final EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("emsPU");
  public static EntityManager em() { return emf.createEntityManager(); }
}
