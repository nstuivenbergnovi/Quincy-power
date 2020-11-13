package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Order;
import com.quincy.database_test.payload.request.OrderRequest;
import com.quincy.database_test.payload.response.MessageResponse;
import com.quincy.database_test.payload.response.OrderResponse;
import com.quincy.database_test.repository.CustomerRepo;
import com.quincy.database_test.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public ResponseEntity<?> createOrderAndAddToCustomer(OrderRequest orderRequest, String customerId) {
       int id = Integer.parseInt(customerId);

        Optional<Customer> customerFromDb = customerRepo.findByCustomerId(id);

        if(customerFromDb.isPresent()) {
            Customer customer = customerFromDb.get();
            Order order = requestToOrder(orderRequest);

            order.setCustomer(customer);
            Order orderFromDb = orderRepo.save(order);
            return ResponseEntity.ok(orderToReponse(orderFromDb));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error. User with id " + id + " not found"));

    }

    public Order requestToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setStatus(orderRequest.getStatus());
        return order;
    }

    public static OrderResponse orderToReponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setCustomerResponse(CustomerService.customerToResponse(order.getCustomer()));

        return orderResponse;
    }


}
