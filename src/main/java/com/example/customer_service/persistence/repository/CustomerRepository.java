package com.example.customer_service.persistence.repository;

import com.example.customer_service.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
    List<CustomerEntity> findByCreateDate(LocalDate createDate);
}
