package com.quincy.database_test.controller;

import com.quincy.database_test.model.Product;
import com.quincy.database_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }
    @GetMapping("/products/a/{id}")
    public Product findProductById(@PathVariable ("id") int id){
        return service.getProductById(id);
    }
    @GetMapping("/products/{name}")
    public Product findProductByName(@PathVariable("name")String name){
        return service.getProductByName(name);
    }
    @PutMapping("/products/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProductById(id);
    }
}
