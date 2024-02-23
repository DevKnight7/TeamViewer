package com.testProject.demo.Service;

import com.testProject.demo.Entity.Order;
import com.testProject.demo.Entity.Product;
import com.testProject.demo.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        Optional<Order> order = orderRepository.findById(updatedOrder.getId());
        Order newOrder = order.get();
        newOrder.setOrderItems(updatedOrder.getOrderItems());
        newOrder.setOrderDate(updatedOrder.getOrderDate());
        return orderRepository.save(newOrder);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}