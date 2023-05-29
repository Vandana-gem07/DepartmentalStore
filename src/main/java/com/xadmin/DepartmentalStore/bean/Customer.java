package com.xadmin.DepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Table(name = "Customer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;



    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;




        // Constructors, getters, and setters
}


















//package com.xadmin.DepartmentalStore.bean;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import jakarta.persistence.Table;
//
////import org.hibernate.annotations.Table;
//
//@Table(name="Customer")
//@Entity
//
//
//public class Customer {
//    @Id
//    //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_id")
//    private Long customerId;
//
//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "contact_number")
//    private String contactNumber;
//
//    @OneToOne
//    @JsonIgnore
//    @JoinColumn(name = "order_id")
//    private Order orders;
//
//    public Customer(){
//        super();
//    }
//
//    public Customer(Long customerId, String fullName, String address, String contactNumber) {
//        this.customerId = customerId;
//        this.fullName = fullName;
//        this.address = address;
//        this.contactNumber = contactNumber;
//    }
//
//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getContactNumber() {
//        return contactNumber;
//    }
//
//    public void setContactNumber(String contactNumber) {
//        this.contactNumber = contactNumber;
//    }
//
//    public Order getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Order orders) {
//        this.orders = orders;
//    }
//}