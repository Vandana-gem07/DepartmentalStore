package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.repository.BackOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BackOrderServiceTest {

    @Mock
    private BackOrderRepository backOrderRepo;

    @InjectMocks
    private BackOrderService backOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllBackOrders() {
        // Create a list of back orders
        List<BackOrder> backOrders = new ArrayList<>();
        backOrders.add(new BackOrder());
        backOrders.add(new BackOrder());

        // Stub the findAll method of backOrderRepository to return the list of back orders
        when(backOrderRepo.findAll()).thenReturn(backOrders);

        // Call the getAllBackOrders method of the backOrderService
        List<BackOrder> result = backOrderService.getAllBackOrders();

        // Verify that the result matches the expected list of back orders
        assertEquals(backOrders, result);
    }

    @Test
    void testGetBackOrderById() {
        // Create a sample back order
        BackOrder backOrder = new BackOrder();
        backOrder.setBackOrderId(1L);

        // Stub the findById method of backOrderRepository to return the sample back order
        when(backOrderRepo.findById(1L)).thenReturn(Optional.of(backOrder));

        // Call the getBackOrderById method of the backOrderService
        BackOrder result = backOrderService.getBackOrderById(1L);

        // Verify that the result matches the expected back order
        assertEquals(backOrder, result);
    }

    @Test
    void testGetBackOrderByIdWithInvalidId() {
        // Stub the findById method of backOrderRepository to return an empty optional
        when(backOrderRepo.findById(1L)).thenReturn(Optional.empty());

        // Call the getBackOrderById method of the backOrderService and expect a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> backOrderService.getBackOrderById(1L));
    }

    @Test
    void testCreateBackOrder() {
        // Create a sample back order
        BackOrder backOrder = new BackOrder();
        backOrder.setBackOrderId(1L);

        // Call the createBackOrder method of the backOrderService
        backOrderService.createBackOrder(backOrder);

        // Verify that the save method of backOrderRepository was called with the sample back order
        verify(backOrderRepo, times(1)).save(backOrder);
    }

    @Test
    void testDeleteBackOrder() {
        // Call the deleteBackOrder method of the backOrderService
        backOrderService.deleteBackOrder(1L);

        // Verify that the deleteById method of backOrderRepository was called with the specified id
        verify(backOrderRepo, times(1)).deleteById(1L);
    }
}
