package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.model.Product;
import com.quincy.database_test.payload.request.CustomerRequest;
import com.quincy.database_test.payload.response.CustomerResponse;
import com.quincy.database_test.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public ResponseEntity<?> saveCustomer(CustomerRequest customerRequest) {

        Customer customer = requestToCustomer(customerRequest);

        return ResponseEntity.ok(customerRepo.save(customer));
        //TODO: Another possible option:
        //return ResponseEntity.ok(new MessageResponse("Customer added successfully"));
    }

    public List <Customer> saveCustomers (List <Customer> customers){
        return customerRepo.saveAll(customers);
    }

    public List <Customer> getCustomers(){
        return customerRepo.findAll();
    }

    public Customer getCustomersById(int customerId){
        return customerRepo.findById(customerId).orElse(null);
    }

    public Customer getCustomerByFirstName(String firstName){
        return customerRepo.findByFirstName(firstName);
    }

    public String deleteCustomerById (Long customerId){
        customerRepo.deleteById(customerId);
        return "item " + customerId + " is deleted";
    }

    public Customer requestToCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setCountry(customerRequest.getCountry());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setEmail(customerRequest.getEmail());

        return customer;
    }

    public Customer updateCustomer(Customer customer){
        Customer existingCustomer = customerRepo.findById(customer.getId()).orElse(customer);
        existingCustomer.setFirstName(existingCustomer.getFirstName());
        existingCustomer.setLastName(existingCustomer.getLastName());
        existingCustomer.setCountry(existingCustomer.getCountry());
        existingCustomer.setPhoneNumber(existingCustomer.getPhoneNumber());
        existingCustomer.setEmail(existingCustomer.getEmail());
        return customerRepo.save(existingCustomer);
    }

    public static CustomerResponse customerToResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setCustomerId(customer.getId());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setCountry(customer.getCountry());
        customerResponse.setEmail(customer.getEmail());

        return customerResponse;
    }


    public List<Product> findByName(String search) {
    }

    public Invoice fetchByIdWithClientWithInvoiceLineWithProduct(Long id) {
    }

    public void saveInvoice(Invoice invoice) {
    }
}
