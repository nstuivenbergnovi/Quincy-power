package com.quincy.database_test.controller;

import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.service.InvoiceLineService;
import com.quincy.database_test.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
    private InvoiceService invoiceService;
    private InvoiceLineService invoiceLineService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Autowired
    public void setInvoiceLineService(InvoiceLineService invoiceLineService) {
        this.invoiceLineService = invoiceLineService;
    }

    @PostMapping("/invoice/{customerId}")
    public ResponseEntity<?> addInvoiceToCustomer(@PathVariable Long customerId,
                                                  @RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.saveInvoiceToCustomer(customerId, invoiceRequest);
    }


    @PostMapping("/invoice/a/{productId}")
    public  ResponseEntity<?> addInvoiceLineToInvoice(@PathVariable Long productId,
                                                      @RequestBody InvoiceLineRequest invoiceLineRequest){
        return invoiceLineService.saveProductsToInvoiceLine(productId, invoiceLineRequest);    }




}
