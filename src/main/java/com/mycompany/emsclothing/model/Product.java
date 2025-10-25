package com.mycompany.emsclothing.model;
import jakarta.persistence.*;

@Entity 
@Table(name = "products")
public class Product {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) 
  private String name;

  @Column(nullable = false) 
  private double price;

  @ManyToOne 
  @JoinColumn(name = "category_id")
  private Category category;

  private String imageUrl;
  private int stock;

  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }

  public Category getCategory() { return category; }
  public void setCategory(Category c) { this.category = c; }
  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String u) { this.imageUrl = u; }
  public int getStock() { return stock; }
  public void setStock(int s) { this.stock = s; }
}
