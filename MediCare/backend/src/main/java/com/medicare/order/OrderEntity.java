package com.medicare.order;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="customer_orders")
public class OrderEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String customerName;
 @Column(nullable=false) private String customerEmail;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal totalAmount;
 @Column(nullable=false) private String status="PLACED";
 @Column(nullable=false) private Instant createdAt=Instant.now();
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER) private List<OrderItem> items=new ArrayList<>();
 protected OrderEntity(){}
 public OrderEntity(String name,String email,BigDecimal total){customerName=name;customerEmail=email;totalAmount=total;}
 public void addItem(OrderItem item){items.add(item);item.setOrder(this);}
 public Long getId(){return id;} public String getCustomerName(){return customerName;} public String getCustomerEmail(){return customerEmail;} public BigDecimal getTotalAmount(){return totalAmount;} public String getStatus(){return status;} public Instant getCreatedAt(){return createdAt;} public List<OrderItem> getItems(){return items;}
}
