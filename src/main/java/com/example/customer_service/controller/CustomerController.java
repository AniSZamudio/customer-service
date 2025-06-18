package com.example.customer_service.controller;

import com.example.customer_service.dto.CustomerDTO;
import com.example.customer_service.service.CustomerService;
import com.example.customer_service.util.exception.AlreadyExistsException;
import com.example.customer_service.util.exception.NoDataFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@CrossOrigin("*")
@Tag(name = "Customer Controller", description = "Operations related to customer management")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully created"),
            @ApiResponse(responseCode = "409", description = "Customer already exists")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerDTO customer) {
        try {
            String response = customerService.createCustomer(customer);
            return ResponseEntity.ok(response);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Retrieve a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "204", description = "Customer not found")
    })
    @GetMapping("/findCustomerById/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(
            @Parameter(description = "ID of the customer", example = "1")
            @PathVariable Long id) {
        try {
            CustomerDTO response = customerService.getCustomer(id);
            return ResponseEntity.ok(response);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Retrieve all customers by creation date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers found"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @PostMapping("/getAllCustomersByCreateDate")
    public ResponseEntity<List<CustomerDTO>> getAllCustomersByCreateDate(
            @Parameter(description = "Creation date in format yyyy-MM-dd", example = "2025-06-10")
            @RequestParam String createDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(createDate, formatter);
            List<CustomerDTO> response = customerService.getAllCustomersByCreateDate(date);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
