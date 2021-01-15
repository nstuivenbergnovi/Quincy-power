package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.model.InvoiceLine;
import com.quincy.database_test.model.Product;
import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.payload.response.MessageResponse;
import com.quincy.database_test.repository.InvoiceLineRepo;
import com.quincy.database_test.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceLineService implements IInvoiceLineService{

    private ProductRepo productRepo;
    private InvoiceLineRepo invoiceLineRepo;

    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    public void setInvoiceLineRepo(InvoiceLineRepo invoiceLineRepo) {
        this.invoiceLineRepo = invoiceLineRepo;
    }

    @Override
    public ResponseEntity<?> saveProductsToInvoiceLine(Long productId, InvoiceLineRequest invoiceLineRequest) {

        Optional<Product> productFromDb = productRepo.findById(productId);

        if(productFromDb.isPresent()) {
            Product product = productFromDb.get();

           InvoiceLine invoiceLine = requestToInvoiceLine(invoiceLineRequest);
            invoiceLine.setProduct(product);
            return ResponseEntity.ok(invoiceLineRepo.save(invoiceLine));
        }

        return ResponseEntity.status(500).body(new MessageResponse("Product not found"));

    }

    private InvoiceLine requestToInvoiceLine(InvoiceLineRequest invoiceLineRequest) {
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setQuantity(invoiceLine.getQuantity());
//        if(invoiceLineRequest.getQuantity()!= null){
//            invoiceLine.setQuantity(invoiceLineRequest.getQuantity());
//        }


        return invoiceLine;

    }
}
