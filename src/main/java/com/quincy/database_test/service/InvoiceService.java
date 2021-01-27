package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.model.InvoiceLine;
import com.quincy.database_test.model.Product;
import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.payload.response.MessageResponse;
import com.quincy.database_test.repository.CustomerRepo;
import com.quincy.database_test.repository.InvoiceLineRepo;
import com.quincy.database_test.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {

    private InvoiceRepo invoiceRepo;
    private CustomerRepo customerRepo;
    private InvoiceLineRepo invoiceLineRepo;

    @Autowired
    public void setInvoiceRepo(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Autowired
    public void setCustomerRepo(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Autowired
    public void setInvoiceLineRepo(InvoiceLineRepo invoiceLineRepo){this.invoiceLineRepo = invoiceLineRepo;}

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

    @Override
    public ResponseEntity<?> saveInvoiceToInvoiceLine(Long invoiceId, InvoiceLineRequest invoiceLineRequest) {
        Optional<Invoice> invoiceFromDb = invoiceRepo.findById(invoiceId);

        if(invoiceFromDb.isPresent()) {
            Invoice invoice = invoiceFromDb.get();

            InvoiceLine invoiceLine = requestToInvoiceLine(invoiceLineRequest);
            invoiceLine.setInvoice(invoice);

            InvoiceLine savedInvoiceLine = invoiceLineRepo.save(invoiceLine);

            savedInvoiceLine.getInvoice().setLines(null);

            //return ResponseEntity.ok(new MessageResponse("Line has been added to invoice"));
            return ResponseEntity.ok(savedInvoiceLine);
        }

        return ResponseEntity.status(500).body(new MessageResponse("Product not found"));
    }

    private Invoice requestToInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setDescription(invoiceRequest.getDescription());
        if(invoiceRequest.getInformation() != null || !invoiceRequest.getInformation().equals("")){
            invoice.setInformation(invoiceRequest.getInformation());
        }


        return invoice;

    }
    private InvoiceLine requestToInvoiceLine(InvoiceLineRequest invoiceLineRequest) {
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setQuantity(invoiceLineRequest.getQuantity());
//        if(invoiceLineRequest.getQuantity()!= null){
//            invoiceLine.setQuantity(invoiceLineRequest.getQuantity());
//        }


        return invoiceLine;

    }
}
