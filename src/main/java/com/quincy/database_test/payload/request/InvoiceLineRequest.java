package com.quincy.database_test.payload.request;

import javax.validation.constraints.NotBlank;

public class InvoiceLineRequest {

    @NotBlank
    private Long InvoiceId;
    private Long ProductId;
    private Long Quantity;

    public Long getQuantity() {
        return Quantity;
    }

    public void setQuantity(Long quantity) {
        Quantity = quantity;
    }

    public Long getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        InvoiceId = invoiceId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }
}