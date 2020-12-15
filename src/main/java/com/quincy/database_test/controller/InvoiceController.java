package com.quincy.database_test.controller;

import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoice/{customerId}")
    public ResponseEntity<?> addInvoiceToCustomer(@PathVariable Long customerId,
                                                  @RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.saveInvoiceToCustomer(customerId, invoiceRequest);
    }
}
