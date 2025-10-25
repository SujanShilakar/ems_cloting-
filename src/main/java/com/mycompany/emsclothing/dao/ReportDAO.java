package com.mycompany.emsclothing.dao;

import jakarta.persistence.EntityManager;
import java.util.List;

public class ReportDAO {
  public static class Row {
    public final Long productId; public final String name;
    public final Long units; public final Double revenue;
    public Row(Long pid, String n, Long u, Double r){ productId=pid; name=n; units=u; revenue=r; }
  }

  public List<Row> topSold(){
    try (EntityManager em = JPAUtil.em()){
      return em.createQuery(
        "SELECT new com.mycompany.emsclothing.dao.ReportDAO$Row(" +
        " p.id, p.name, SUM(oi.qty), SUM(oi.price*oi.qty)) " +
        "FROM OrderItem oi JOIN oi.product p GROUP BY p.id, p.name ORDER BY SUM(oi.qty) DESC",
        Row.class).getResultList();
    }
  }
}
