package com.example.customer_service.controller;

import com.example.customer_service.dto.CustomerAddressDTO;
import com.example.customer_service.service.CustomerAddressService;
import com.example.customer_service.util.exception.NoDataFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customerAddress")
@CrossOrigin("*")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    public CustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    @Operation(summary = "Add a new customer address", description = "Adds a new address associated with a customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address successfully created"),
            @ApiResponse(responseCode = "204", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/addAddress")
    public ResponseEntity<String> addCustomerAddress(@RequestBody @Valid CustomerAddressDTO customerAddress) {
        try {
            String response = customerAddressService.addCustomerAddress(customerAddress);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Get customer addresses by customer ID", description = "Retrieves all addresses associated with a given customer ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses successfully retrieved"),
            @ApiResponse(responseCode = "204", description = "No addresses found for the customer")
    })
    @GetMapping("/getAddressesByCustomerId/{customerId}")
    public ResponseEntity<List<CustomerAddressDTO>> getAllCustomerAddressesByCustomerId(@PathVariable Long customerId) {
        try {
            List<CustomerAddressDTO> response = customerAddressService.getAllCustomerAddressesByCustomerId(customerId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Delete a customer address", description = "Deletes a specific customer address based on the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @DeleteMapping("/deleteAddress")
    public ResponseEntity<String> deleteCustomerAddress(@RequestBody @Valid CustomerAddressDTO customerAddress) {
        return new ResponseEntity<>(customerAddressService.deleteCustomerAddress(customerAddress), HttpStatus.OK);
    }

}
