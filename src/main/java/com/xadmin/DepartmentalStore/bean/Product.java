
package com.xadmin.DepartmentalStore.bean;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Product")
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double price;

    @Column(name = "expiry")
    private Date expiry;

    @Column(name = "count")
    private int count;

    @Column(name = "availability")
    private boolean availability;

}



