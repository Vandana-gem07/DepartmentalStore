package com.xadmin.DepartmentalStore.repository;

import com.xadmin.DepartmentalStore.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

}
