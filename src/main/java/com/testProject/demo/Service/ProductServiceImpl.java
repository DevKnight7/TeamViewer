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

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Optional<Product> product = productRepository.findById(updatedProduct.getId());
        Product newProduct = product.get();
        newProduct.setName(updatedProduct.getName());
        newProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
