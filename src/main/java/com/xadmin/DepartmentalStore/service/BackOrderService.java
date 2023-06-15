package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.repository.BackOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BackOrderService {

    @Autowired
    private BackOrderRepository backOrderRepository;

    public List<BackOrder> getAllBackOrders() {
        return backOrderRepository.findAll();
    }

    public BackOrder getBackOrderById(Long backOrderId) {
        return backOrderRepository.findById(backOrderId).orElseThrow(NoSuchElementException::new);
    }

    public void createBackOrder(BackOrder backOrder) {
        backOrderRepository.save(backOrder);
    }

    public void deleteBackOrder(Long backOrderId) {
        backOrderRepository.deleteById(backOrderId);
    }
}