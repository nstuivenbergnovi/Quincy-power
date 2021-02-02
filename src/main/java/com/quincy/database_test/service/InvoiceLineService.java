package com.quincy.database_test.service;

import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.model.InvoiceLine;
import com.quincy.database_test.model.Product;
import com.quincy.database_test.payload.request.InvoiceLineRequest;
import com.quincy.database_test.payload.request.InvoiceRequest;
import com.quincy.database_test.payload.response.MessageResponse;
import com.quincy.database_test.repository.InvoiceLineRepo;
import com.quincy.database_test.repository.InvoiceRepo;
import com.quincy.database_test.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InvoiceLineService implements IInvoiceLineService{

    private ProductRepo productRepo;
    private InvoiceLineRepo invoiceLineRepo;
    private InvoiceRepo invoiceRepo;

    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    public void setInvoiceLineRepo(InvoiceLineRepo invoiceLineRepo) {
        this.invoiceLineRepo = invoiceLineRepo;
    }

    @Autowired
    public void setInvoiceRepo(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Override
    public ResponseEntity<?> saveProductsToInvoiceLine(Long productId, Long invoiceId, InvoiceLineRequest invoiceLineRequest) {

        Optional<Product> productFromDb = productRepo.findById(productId);
        Optional<Invoice> invoiceFromDb = invoiceRepo.findById(invoiceId);

        if(productFromDb.isPresent() && invoiceFromDb.isPresent()) {
            Product product = productFromDb.get();
            Invoice invoice = invoiceFromDb.get();

           InvoiceLine invoiceLine = requestToInvoiceLine(invoiceLineRequest);
            invoiceLine.setProduct(product);
            invoiceLine.setInvoice(invoice);

            InvoiceLine savedInvoiceLine = invoiceLineRepo.save(invoiceLine);

            //savedInvoiceLine.getProduct().setInvoiceLines(null);
            //savedInvoiceLine.getInvoice().setLines(null);
            //savedInvoiceLine.getInvoice().getCustomer().setInvoices(new ArrayList<>());

            StringBuilder sb = new StringBuilder(product.getName())
                    .append(" is toegevoegd aan factuur: ")
                    .append(invoice.getId());

            return ResponseEntity.ok(new MessageResponse(sb.toString()));
            //return ResponseEntity.ok(savedInvoiceLine);
        }

        return ResponseEntity.status(500).body(new MessageResponse("Product not found"));

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
