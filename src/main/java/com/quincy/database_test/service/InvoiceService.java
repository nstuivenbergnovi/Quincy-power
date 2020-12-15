package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.repository.CustomerRepo;
import com.quincy.database_test.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class InvoiceService implements IInvoiceService {

    private InvoiceRepo invoiceRepo;
    private CustomerRepo customerRepo;

    @Autowired
    public void setInvoiceRepo(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Autowired
    public void setCustomerRepo(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public ResponseEntity<?> saveInvoiceToCustomer(Long customerId, InvoiceRequest invoiceRequest) {
        //todo
        //1:Request ombouwen
        //2: Klant ophalen en invoice toevoegen.


        Optional<Customer> customerFromDb = customerRepo.findById(customerId);

        if(customerFromDb.isPresent()) {
            Customer customer = customerFromDb.get();
            customer.addOrder(null); //todo

            customerRepo.save(customer);
        }


        //TODO RETURN
        return null;
    }
}
