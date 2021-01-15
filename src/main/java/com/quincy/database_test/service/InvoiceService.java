package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.payload.response.MessageResponse;
import com.quincy.database_test.repository.CustomerRepo;
import com.quincy.database_test.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

        Optional<Customer> customerFromDb = customerRepo.findById(customerId);

        if(customerFromDb.isPresent()) {
            Customer customer = customerFromDb.get();

            Invoice invoice = requestToInvoice(invoiceRequest);
            invoice.setCustomer(customer);
            return ResponseEntity.ok(invoiceRepo.save(invoice));
        }

        return ResponseEntity.status(500).body(new MessageResponse("User not found"));
    }

    private Invoice requestToInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setDescription(invoiceRequest.getDescription());
        if(invoiceRequest.getInformation() != null || !invoiceRequest.getInformation().equals("")){
            invoice.setInformation(invoiceRequest.getInformation());
        }


        return invoice;

    }
}
