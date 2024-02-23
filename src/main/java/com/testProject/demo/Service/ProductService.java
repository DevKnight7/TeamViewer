package com.testProject.demo.Service;

import com.testProject.demo.Entity.Product;
import com.testProject.demo.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id) throws ChangeSetPersister.NotFoundException;

    Product createProduct(Product product);

    Product updateProduct(Product updatedProduct) throws ChangeSetPersister.NotFoundException;

    void deleteProductById(Long id);
}

// Similar services for Order and OrderItem