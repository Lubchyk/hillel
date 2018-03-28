package com.hillel.finalWork.repositories;

import com.hillel.finalWork.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
