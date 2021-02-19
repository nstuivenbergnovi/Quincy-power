package com.quincy.database_test.payload.response;

import com.quincy.database_test.model.Invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InvoiceResponse {
    private String lastName;
    private String firstName;
    private Date createdAt;
    private List<InvoiceLineResponse> invoiceLine;
    private BigDecimal total;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<InvoiceLineResponse> getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(List<InvoiceLineResponse> invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
