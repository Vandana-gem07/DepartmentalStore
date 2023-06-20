package com.xadmin.DepartmentalStore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
//import javax.persistence.Table;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

@Table(name = "Customer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "contactNumber")
    private String contactNumber;

    public Customer (Long customerId ,String fullName, String address, String contactNumber) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.orders = new ArrayList<>();
    }


    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "customerId")
    private List<Order> orders = new ArrayList<>();


}

