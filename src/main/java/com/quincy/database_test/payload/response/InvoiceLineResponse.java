package com.quincy.database_test.payload.response;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceLineResponse {
    private String productName;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal totalPrice;


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totPrice) {
        this.totalPrice = totPrice;
    }
}
