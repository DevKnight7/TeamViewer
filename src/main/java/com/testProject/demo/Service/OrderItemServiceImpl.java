package com.testProject.demo.Service;


import com.testProject.demo.Entity.Order;
import com.testProject.demo.Entity.OrderItem;
import com.testProject.demo.Entity.Product;
import com.testProject.demo.Repository.OrderItemRepository;
import com.testProject.demo.Repository.OrderRepository;
import com.testProject.demo.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final OrderRepository orderRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        Product product = productRepository.findById(orderItem.getProduct().getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        orderItem.setProduct(product);
        Order order = orderRepository.findById(orderItem.getOrder().getId()).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        orderItem.setOrder(order);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        OrderItem newOrderItem = orderItem.get();
        newOrderItem.setQuantity(updatedOrderItem.getQuantity());
        newOrderItem.setOrder(updatedOrderItem.getOrder());
        newOrderItem.setProduct(updatedOrderItem.getProduct());
        return orderItemRepository.save(newOrderItem);
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

}