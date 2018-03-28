package com.hillel.finalWork.service;

import com.hillel.finalWork.model.Product;
import com.hillel.finalWork.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean updateProduct(Product product) {
        return productRepository.saveAndFlush(product) != null ? true : false;
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public Product findById(int id) {
        return productRepository.findOne(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
