package com.example.customer_service.persistence.repository;

import com.example.customer_service.persistence.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Long> {

    List<CustomerAddressEntity> findByCustomerId(Long customerId);
    Optional<CustomerAddressEntity> findByCustomerIdAndStreetAndCityAndPostalCodeAndCountry
            (Long customerId, String street, String city, int postalCode, String country);
}
