package com.testProject.demo.Service;

import com.testProject.demo.Entity.Order;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id) throws ChangeSetPersister.NotFoundException;
    Order createOrder(Order order);
    Order updateOrder(Order order) throws ChangeSetPersister.NotFoundException;
    void deleteOrderById(Long id);
}
