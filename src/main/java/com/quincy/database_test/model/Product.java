package com.quincy.database_test.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    private String name;
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="product_id")
    private List<InvoiceLine> invoiceLines;


    public Product() {
    }

    public Product(Long productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}
