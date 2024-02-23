package com.testProject.demo.Controller;


import com.testProject.demo.Entity.Order;
import com.testProject.demo.Service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Orders", description = "Order API calls")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    @Operation(summary = "Get a list of all orders", responses = {
            @ApiResponse(description = "A list of orders", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Order.class))))})
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    @Operation(summary = "Get an order by ID", responses = {
            @ApiResponse(description = "An order", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")})
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders")
    @Operation(summary = "Create a new order", requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))), responses = {
            @ApiResponse(description = "Created", responseCode = "201")})
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/orders/{id}")
    @Operation(summary = "Update an existing order", requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))), responses = {
            @ApiResponse(description = "Updated", responseCode = "200")})
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody Order updatedOrder) throws ChangeSetPersister.NotFoundException {
        Order order = orderService.getOrderById(id);
        order.setOrderDate(updatedOrder.getOrderDate());
        Order savedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/orders/{id}")
    @Operation(summary = "Delete an order by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted")})
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}