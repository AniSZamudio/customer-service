package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerAddressDTO;
import com.example.customer_service.util.exception.NoDataFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerAddressService {

    String addCustomerAddress(CustomerAddressDTO customerAddress) throws NoDataFoundException;
    List<CustomerAddressDTO> getAllCustomerAddressesByCustomerId(Long customerId) throws NoDataFoundException;
    String deleteCustomerAddress(CustomerAddressDTO customerAddress);
}
