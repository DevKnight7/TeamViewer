package com.testProject.demo.Controller;

import com.testProject.demo.Entity.Product;
import com.testProject.demo.Service.ProductService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Products", description = "Product API calls")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    @Operation(summary = "Get a list of all products", responses = {
            @ApiResponse(description = "A list of products", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))))})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get a product by ID", responses = {
            @ApiResponse(description = "A product", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    @Operation(summary = "Create a new product", requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))), responses = {
            @ApiResponse(description = "Created", responseCode = "201")})
    public ResponseEntity<Product> createProduct(@org.springframework.web.bind.annotation.RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/products/{id}")
    @Operation(summary = "Update an existing product", requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))), responses = {
            @ApiResponse(description = "Updated", responseCode = "200")})
    public ResponseEntity<Product> updateProduct(@Valid @org.springframework.web.bind.annotation.RequestBody  Product updatedProduct) throws ChangeSetPersister.NotFoundException {
//        Product product = productService.getProductById(id);
//        product.setName(updatedProduct.getName());
//        product.setPrice(updatedProduct.getPrice());
//        Product savedProduct = productService.updateProduct(product);
        // Construct the URI for the newly created resource
        return ResponseEntity.ok(productService.updateProduct(updatedProduct));
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete a product by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted")})
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}