package com.medicare.medicine;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="medicines")
public class Medicine {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=140) private String name;
 @Column(nullable=false,length=80) private String category;
 @Column(length=500) private String description;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal price;
 @Column(name="stock_quantity",nullable=false) private Integer stockQuantity;
 @Column(name="prescription_required",nullable=false) private boolean prescriptionRequired;
 protected Medicine(){}
 public Medicine(String name,String category,String description,BigDecimal price,Integer stockQuantity,boolean prescriptionRequired){this.name=name;this.category=category;this.description=description;this.price=price;this.stockQuantity=stockQuantity;this.prescriptionRequired=prescriptionRequired;}
 public Long getId(){return id;} public String getName(){return name;} public String getCategory(){return category;} public String getDescription(){return description;} public BigDecimal getPrice(){return price;} public Integer getStockQuantity(){return stockQuantity;} public boolean isPrescriptionRequired(){return prescriptionRequired;}
 public void setStockQuantity(Integer n){stockQuantity=n;}
}
