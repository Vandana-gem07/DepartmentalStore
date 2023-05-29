package com.xadmin.DepartmentalStore.bean;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Orders")
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "order_ts")
    private Date orderTimestamp;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "productId")
    private int productId;

    // Constructors, getters, and setters
}























//package com.xadmin.DepartmentalStore.bean;
//import jakarta.persistence.*;
//import jakarta.persistence.Table;
//import java.util.Date;
//
//@Table(name = "Orders")
//@Entity
//public class Order {
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_id")
//    private Long orderId;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id",referencedColumnName= "product_id")
//    private Product product;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_id",referencedColumnName= "customer_id")
//    private Customer customer;
//
//    @Column(name = "order_ts")
//    private Date orderTimestamp;
//
//    @Column(name = "quantity")
//    private int quantity;
//
//    public Order(){
//        super();
//    }
//
//    public Order(Long orderId, Date orderTimestamp, int quantity) {
//        this.orderId = orderId;
//        this.orderTimestamp = orderTimestamp;
//        this.quantity = quantity;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//        product.setOrders(this);
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//        customer.setOrders(this);
//    }
//
//    public Date getOrderTimestamp() {
//        return orderTimestamp;
//    }
//
//    public void setOrderTimestamp(Date orderTimestamp) {
//        this.orderTimestamp = orderTimestamp;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//}
