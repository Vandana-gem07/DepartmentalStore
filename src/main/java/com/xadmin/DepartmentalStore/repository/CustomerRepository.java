package com.xadmin.DepartmentalStore.repository;

import com.xadmin.DepartmentalStore.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
