package com.quincy.database_test.repository;

import com.quincy.database_test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);


}
