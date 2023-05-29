package com.xadmin.DepartmentalStore.repository;

import com.xadmin.DepartmentalStore.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
