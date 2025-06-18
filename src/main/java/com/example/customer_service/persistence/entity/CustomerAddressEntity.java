package com.example.customer_service.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer_address")
public class CustomerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column
    private String street;
    @Column
    private String city;
    @Column(name = "postal_code")
    private int postalCode;
    @Column
    private String country;
}
