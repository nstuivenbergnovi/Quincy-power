package com.quincy.database_test.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_tbl")
public class Order {

    @Id
    @GeneratedValue
    private int orderNumber;

    private String status;

    @ManyToOne
    private Customer customer;

    public Order() {
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
