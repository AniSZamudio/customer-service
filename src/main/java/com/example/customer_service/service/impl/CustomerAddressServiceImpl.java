package com.example.customer_service.service.impl;

import com.example.customer_service.dto.CustomerAddressDTO;
import com.example.customer_service.persistence.entity.CustomerAddressEntity;
import com.example.customer_service.persistence.repository.CustomerAddressRepository;
import com.example.customer_service.persistence.repository.CustomerRepository;
import com.example.customer_service.service.CustomerAddressService;
import com.example.customer_service.util.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer with id {} not exist";

    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;

    public CustomerAddressServiceImpl(CustomerAddressRepository customerAddressRepository, CustomerRepository customerRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public String addCustomerAddress(CustomerAddressDTO customerAddress) throws NoDataFoundException {

        boolean existCustomer = customerRepository.existsById(customerAddress.getCustomerId());
        if (!existCustomer) {
            log.info(CUSTOMER_NOT_FOUND_MESSAGE, customerAddress.getCustomerId());
            throw new NoDataFoundException("Customer with id" + customerAddress.getCustomerId() + " not exist");
        } else {
            log.info("Adding customer address");
            CustomerAddressEntity customerAddressEntity = mapToCustomerAddressEntity(customerAddress);
            customerAddressRepository.save(customerAddressEntity);
            return "Customer address add in data base with id:  " + customerAddressEntity.getId().toString();
        }
    }

    @Override
    public List<CustomerAddressDTO> getAllCustomerAddressesByCustomerId(Long customerId) throws NoDataFoundException {

        boolean existCustomer = customerRepository.existsById(customerId);
        if (!existCustomer) {
            log.info(CUSTOMER_NOT_FOUND_MESSAGE, customerId);
            throw new NoDataFoundException("Customer with id" + customerId + " not exist");
        } else {
            log.info("process getAllCustomerAddressesByCustomerId");
            List<CustomerAddressEntity> customerAddressEntities = customerAddressRepository.findByCustomerId(customerId);
            List<CustomerAddressDTO> customerAddressDTOS = new ArrayList<>();
            customerAddressEntities.forEach(entity -> customerAddressDTOS.add(mapToCustomerAddressDTO(entity)));
            return customerAddressDTOS;
        }
    }

    @Override
    public String deleteCustomerAddress(CustomerAddressDTO customerAddress) {
        CustomerAddressEntity deleteAddress = customerAddressRepository.findByCustomerIdAndStreetAndCityAndPostalCodeAndCountry
                (customerAddress.getCustomerId(), customerAddress.getStreet(), customerAddress.getCity(), customerAddress.getPostalCode(),
                        customerAddress.getCountry()).orElse(null);
        if (deleteAddress == null) {
            log.info(CUSTOMER_NOT_FOUND_MESSAGE, customerAddress.getCustomerId());
            return "Address with customer id " + customerAddress.getCustomerId() + " not exist";
        } else {
            customerAddressRepository.delete(deleteAddress);
            return "Address successfully deleted";
        }
    }

    private CustomerAddressEntity mapToCustomerAddressEntity(CustomerAddressDTO customerAddressDTO) {
        CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
        customerAddressEntity.setCustomerId(customerAddressDTO.getCustomerId());
        customerAddressEntity.setCity(customerAddressDTO.getCity());
        customerAddressEntity.setCountry(customerAddressDTO.getCountry());
        customerAddressEntity.setStreet(customerAddressDTO.getStreet());
        customerAddressEntity.setPostalCode(customerAddressDTO.getPostalCode());
        return customerAddressEntity;
    }

    private CustomerAddressDTO mapToCustomerAddressDTO(CustomerAddressEntity customerAddressEntity) {
        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCustomerId(customerAddressEntity.getCustomerId());
        customerAddressDTO.setCity(customerAddressEntity.getCity());
        customerAddressDTO.setCountry(customerAddressEntity.getCountry());
        customerAddressDTO.setStreet(customerAddressEntity.getStreet());
        customerAddressDTO.setPostalCode(customerAddressEntity.getPostalCode());
        return customerAddressDTO;
    }
}
