package com.xadmin.DepartmentalStore.repository;

import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackOrderRepository extends JpaRepository<BackOrder, Long> {
    BackOrder findByOrder(Order order);
}