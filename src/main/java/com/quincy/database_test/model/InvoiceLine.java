package com.quincy.database_test.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="invoice_lines")
public class InvoiceLine implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id")
    private Long id;

    private int quantity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BigDecimal calculatePrice() {

        BigDecimal price = new BigDecimal(String.valueOf(product.getPrice()));
        BigDecimal amount = new BigDecimal(quantity);

        return price.multiply(amount);

    }

}
