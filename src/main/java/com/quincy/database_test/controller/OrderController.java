package com.quincy.database_test.controller;

import com.quincy.database_test.payload.request.OrderRequest;
import com.quincy.database_test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/orders/{customerId}")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest order, @PathVariable("customerId") String customerId) {
        return orderService.createOrderAndAddToCustomer(order, customerId);
    }
}
