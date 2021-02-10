package com.quincy.database_test.service;

import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import org.springframework.http.ResponseEntity;

public interface IInvoiceService {

    ResponseEntity<?> saveInvoiceToCustomer(Long customerId, InvoiceRequest invoiceRequest);
    ResponseEntity<?> saveInvoiceToInvoiceLine(Long invoiceId, InvoiceLineRequest invoiceLineRequest);
    ResponseEntity<?> getInvoiceInformation(Long invoiceId);
}
