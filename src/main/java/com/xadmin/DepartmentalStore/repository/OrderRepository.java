package com.xadmin.DepartmentalStore.repository;

import com.xadmin.DepartmentalStore.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
