package com.mycompany.emsclothing.model;
import jakarta.persistence.*;

@Entity @Table(name="users")
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String email;
  @Column(nullable=false) private String passwordHash;
  @Column(nullable=false) private String role; // "ADMIN" or "CUSTOMER"
  @Column(name="full_name") private String fullName;
  @Column private String phone;
  @Column private String address;
  public Long getId(){return id;}
  public String getEmail(){return email;} public void setEmail(String e){email=e;}
  public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String h){passwordHash=h;}
  public String getRole(){return role;} public void setRole(String r){role=r;}
  public String getFullName(){ return fullName; }
public void setFullName(String v){ fullName = v; }
public String getPhone(){ return phone; }
public void setPhone(String v){ phone = v; }
public String getAddress(){ return address; }
public void setAddress(String v){ address = v; }
}
