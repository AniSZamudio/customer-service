package com.example.customer_service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerAddressDTO {

    @NotNull
    private Long customerId;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotNull
    private int postalCode;
    @NotEmpty
    private String country;
}
