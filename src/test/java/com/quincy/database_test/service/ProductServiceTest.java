package com.quincy.database_test.service;

import com.quincy.database_test.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void saveProduct() {
        Product product = new Product();
        product.setName("brood");
        Assertions.assertFalse(product.getName().isEmpty());
    }
}