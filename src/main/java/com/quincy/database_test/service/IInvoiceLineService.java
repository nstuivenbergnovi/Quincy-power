package com.quincy.database_test.service;

import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import org.springframework.http.ResponseEntity;

public interface IInvoiceLineService {
    ResponseEntity<?> saveProductsToInvoiceLine(Long productId, Long invoiceId, InvoiceLineRequest invoiceLineRequest);

}
