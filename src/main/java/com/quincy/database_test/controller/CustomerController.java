package com.quincy.database_test.controller;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.payload.request.CustomerRequest;
import com.quincy.database_test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customer){
        return service.saveCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return  service.getCustomers();
    }
    @GetMapping("/customers/a/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer findCustomerById(@PathVariable("customerId") int customerId){
        return service.getCustomersById(customerId);
    }
    @GetMapping("/customers/{firstName}")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer findCustomerByName(@PathVariable("firstName") String firstName){
        return service.getCustomerByFirstName(firstName);
    }
    @PutMapping("/customers/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer updateCustomer (@RequestBody Customer customer){
        return service.updateCustomer(customer);
    }

    @DeleteMapping("/customers/delete/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCustomer(@PathVariable long customerId){
        return service.deleteCustomerById(customerId);
    }
}
