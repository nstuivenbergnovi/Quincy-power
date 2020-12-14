package com.quincy.database_test.controller;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.payload.request.CustomerRequest;
import com.quincy.database_test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;


    // TODO ResponseEntity voorbeeld (met DTO: SignupRequest)
    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customer){
        return service.saveCustomer(customer);
    }


    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return  service.getCustomers();
    }
    @GetMapping("/customers/a/{customerId}")
    public Customer findCustomerById(@PathVariable("customerId") int customerId){
        return service.getCustomersById(customerId);
    }
    @GetMapping("/customers/{firstName}")
    public Customer findCustomerByName(@PathVariable("firstName") String firstName){
        return service.getCustomerByFirstName(firstName);
    }
    @PutMapping("/customers/update")
    public Customer updateCustomer (@RequestBody Customer customer){
        return service.updateCustomer(customer);
    }

    @DeleteMapping("/customers/delete/{customerId}")
    public String deleteCustomer(@PathVariable long customerId){
        return service.deleteCustomerById(customerId);
    }
}
