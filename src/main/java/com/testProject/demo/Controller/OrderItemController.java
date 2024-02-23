package com.testProject.demo.Controller;


import com.testProject.demo.Entity.OrderItem;
import com.testProject.demo.Service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Order Items", description = "Order Items API calls")
public class OrderItemController {
    @Autowired
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // GET /api/order-items
    @GetMapping("/order-items")
    @Operation(summary = "Get a list of all order items", responses = {
            @ApiResponse(description = "A list of order items", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderItem.class))))})
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // GET /api/order-items/{id}
    @GetMapping("/order-items/{id}")
    @Operation(summary = "Get an order item by ID", responses = {
            @ApiResponse(description = "An order item", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItem.class))),
            @ApiResponse(responseCode = "404", description = "Order item not found")})
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem.get());
    }

    // POST /api/order-items
    @PostMapping("/order-items")
    @Operation(summary = "Create a new order item", requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItem.class))), responses = {
            @ApiResponse(description = "Created", responseCode = "201")})
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    // PUT /api/order-items/{id}
    @PutMapping("/order-items/{id}")
    @Operation(summary = "Update an existing order item", requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItem.class))), responses = {
            @ApiResponse(description = "Updated", responseCode = "200")})
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem updatedOrderItem) {

        return ResponseEntity.ok(orderItemService.updateOrderItem(id, updatedOrderItem));
    }

    // DELETE /api/order-items/{id}
    @DeleteMapping("/order-items/{id}")
    @Operation(summary = "Delete an order item by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted")})
    public ResponseEntity<Void> deleteOrderItemById(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
