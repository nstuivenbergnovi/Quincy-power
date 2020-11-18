package com.quincy.database_test.service;

import com.quincy.database_test.model.Product;
import com.quincy.database_test.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product saveProduct(Product product){
        return productRepo.save(product);
    }

    public List <Product> saveProducts (List <Product> products){
        return productRepo.saveAll(products);
    }

    public List <Product> getProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return productRepo.findByName(name);
    }

    public String deleteProductById (int id){
        productRepo.deleteById(id);
        return "item " + id + "is deleted";
    }

    public Product updateProduct(Product product){
        Product existingProduct = productRepo.findById(product.getProductId()).orElse(product);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepo.save(existingProduct);
    }

}
