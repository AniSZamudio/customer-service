package com.example.customer_service.service.impl;

import com.example.customer_service.dto.CustomerDTO;
import com.example.customer_service.persistence.entity.CustomerAddressEntity;
import com.example.customer_service.persistence.entity.CustomerEntity;
import com.example.customer_service.persistence.repository.CustomerRepository;
import com.example.customer_service.service.CustomerAddressService;
import com.example.customer_service.service.CustomerService;
import com.example.customer_service.util.exception.AlreadyExistsException;
import com.example.customer_service.util.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerAddressService customerAddressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerAddressService customerAddressService) {
        this.customerRepository = customerRepository;
        this.customerAddressService = customerAddressService;
    }

    @Override
    public String createCustomer(CustomerDTO customer) throws AlreadyExistsException {
        CustomerEntity customerEntity = customerRepository.findByFirstNameAndLastNameAndEmail(
                customer.getFirstName(), customer.getLastName(), customer.getEmail()).orElse(null);

        if (customerEntity != null) {
            log.info("Customer already exists in data base");
            throw new AlreadyExistsException("Customer already exist in data base");
        } else {
            log.info("Creating new customer");
            CustomerEntity customerEntityEntity = mapToCustomerEntity(customer);
            customerRepository.save(customerEntityEntity);
            return "Customer created";
        }
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws NoDataFoundException {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElse(null);
        if (customerEntity == null) {
            log.info("Customer not found");
            throw new NoDataFoundException("Customer not found");
        } else {
            log.info("Customer found");
            return mapToCustomerDTO(customerEntity);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByCreateDate(LocalDate createDate) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository.findByCreateDate(createDate);
        customerEntities.forEach(customerEntity -> {
                    CustomerDTO customerDTO;
                    customerDTO = mapToCustomerDTO(customerEntity);
                    try {
                        customerDTO.setAddress(customerAddressService.getAllCustomerAddressesByCustomerId(customerEntity.getId()));
                    } catch (NoDataFoundException e) {
                        log.info("Customer not found");
                    }
                    customerDTOList.add(customerDTO);
                }
        );
        return customerDTOList;
    }

    private CustomerEntity mapToCustomerEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDTO.getFirstName());
        customerEntity.setLastName(customerDTO.getLastName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setCreateDate(LocalDate.now());
        return customerEntity;
    }

    private CustomerDTO mapToCustomerDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setFirstName(customerEntity.getFirstName());
        customerDTO.setLastName(customerEntity.getLastName());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setCreateDate(customerEntity.getCreateDate());
        return customerDTO;
    }
}
