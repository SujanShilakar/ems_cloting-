package com.mycompany.emsclothing.model;
import jakarta.persistence.*;

@Entity @Table(name="categories")
public class Category {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
  @Column(nullable=false, unique=true) private String name;
  public Integer getId(){return id;}
  public String getName(){return name;} public void setName(String n){name=n;}
}
