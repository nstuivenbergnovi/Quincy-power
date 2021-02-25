package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.model.InvoiceLine;
import com.quincy.database_test.model.Product;
import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.payload.response.CustomerResponse;
import com.quincy.database_test.payload.response.InvoiceLineResponse;
import com.quincy.database_test.payload.response.InvoiceResponse;
import com.quincy.database_test.payload.response.MessageResponse;
import com.quincy.database_test.repository.CustomerRepo;
import com.quincy.database_test.repository.InvoiceLineRepo;
import com.quincy.database_test.repository.InvoiceRepo;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

            return ResponseEntity.ok(savedInvoiceLine);
        }

        return ResponseEntity.status(500).body(new MessageResponse("Product not found"));
    }

    @Override
    public ResponseEntity<?> getInvoiceInformation(Long invoiceId) {
        Optional<Invoice> invoiceFromDb = invoiceRepo.findById(invoiceId);

        InvoiceResponse invoiceresponse = new InvoiceResponse();

        List<InvoiceLineResponse> nieuweLijst = new ArrayList<>();

        if (invoiceFromDb.isPresent()) {
            Invoice invoice = invoiceFromDb.get();
            List<InvoiceLine> lines = (invoice.getLines());
            InvoiceLineResponse invoiceLineResponse;
            for (InvoiceLine invoiceLine : lines){
                invoiceLineResponse = new InvoiceLineResponse();
                invoiceLineResponse.setProductName(invoiceLine.getProduct().getName());
                invoiceLineResponse.setPrice(invoiceLine.getProduct().getPrice());
                invoiceLineResponse.setQuantity(invoiceLine.getQuantity());
                BigDecimal quantity = new BigDecimal(invoiceLine.getQuantity());
                invoiceLineResponse.setTotalPrice(invoiceLine.getProduct().getPrice().multiply(quantity));
                nieuweLijst.add(invoiceLineResponse);
            }
            invoiceresponse.setInvoiceLine(nieuweLijst);

            invoiceresponse.setLastName(invoice.getCustomer().getLastName());
            invoiceresponse.setFirstName(invoice.getCustomer().getFirstName());

            invoiceresponse.setCreatedAt(invoice.getCreatedAt());

                BigDecimal total = new BigDecimal("0");
                for(InvoiceLineResponse line : nieuweLijst) {
                    BigDecimal amount = new BigDecimal(line.getQuantity());
                    BigDecimal total2 = line.getPrice().multiply(amount);
                    total = total.add(total2);
                }
                invoiceresponse.setTotal(total);

            return ResponseEntity.status(200).body(invoiceresponse);

        } else {
            return ResponseEntity.status(500).body(new MessageResponse("Invoice not found"));
        }
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
        return invoiceLine;

    }
}
