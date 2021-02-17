package com.quincy.database_test.payload.response;

import java.math.BigDecimal;

public class InvoiceLineResponse {
    private Long invoiceId;
    private Long productId;
    private String productName;
    private BigDecimal price;
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
