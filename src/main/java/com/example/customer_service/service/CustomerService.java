package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerDTO;
import com.example.customer_service.util.exception.AlreadyExistsException;
import com.example.customer_service.util.exception.NoDataFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CustomerService {
    String createCustomer(CustomerDTO customer) throws AlreadyExistsException;
    CustomerDTO getCustomer(Long customerId) throws NoDataFoundException;
    List<CustomerDTO> getAllCustomersByCreateDate(LocalDate createDate);
}
