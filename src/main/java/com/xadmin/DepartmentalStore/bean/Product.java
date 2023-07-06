
package com.xadmin.DepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "Product")
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "productDescription")
    private String productDesc;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private double price;


    @Column(name = "count")
    private int count;


    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "productId")
    private List<Order> orders = new ArrayList<>();

    public Product(Long productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }


}



