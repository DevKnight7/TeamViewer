package com.testProject.demo.Service;


import com.testProject.demo.Entity.OrderItem;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    List<OrderItem> getAllOrderItems();

    Optional<OrderItem> getOrderItemById(Long id);

    OrderItem createOrderItem(OrderItem orderItem);

    OrderItem saveOrderItem(OrderItem orderItem);

    OrderItem updateOrderItem(Long id, OrderItem orderItem);
    void deleteOrderItem(Long id);

//    Page<OrderItem> getOrderItemsByOrderId(Long orderId, Pageable pageable);

}