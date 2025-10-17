package com.mycompany.emsclothing.model;

public class CartItem {
    private Long productId;
    private String name;
    private double unitPrice;
    private int qty;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getLineTotal() { return unitPrice * qty; }
}
