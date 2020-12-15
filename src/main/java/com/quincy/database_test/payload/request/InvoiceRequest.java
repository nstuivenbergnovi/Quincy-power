package com.quincy.database_test.payload.request;

import javax.validation.constraints.NotBlank;

public class InvoiceRequest {

    @NotBlank
    private String description;
    private String information;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
