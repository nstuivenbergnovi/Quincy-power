package com.quincy.database_test.controller;

import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.payload.response.InvoiceResponse;
import com.quincy.database_test.service.InvoiceLineService;
import com.quincy.database_test.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/invoice/a/{productId}/{invoiceId}")
    public  ResponseEntity<?> addInvoiceLineToInvoice(@PathVariable Long productId,
                                                      @PathVariable Long invoiceId,
                                                      @RequestBody InvoiceLineRequest invoiceLineRequest){
        return invoiceLineService.saveProductsToInvoiceLine(productId,invoiceId, invoiceLineRequest);
    }

   @GetMapping("/invoice/a/{invoiceId}")
    public  ResponseEntity<?> recieveInvoice (@PathVariable Long invoiceId){
        return invoiceService.getInvoiceInformation(invoiceId);
    }





}
