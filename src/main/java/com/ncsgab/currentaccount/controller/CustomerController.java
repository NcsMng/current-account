package com.ncsgab.currentaccount.controller;

import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CustomerDto>> getAllCustomers() {
        log.debug("NEW GET REQUEST TO /v1/customer");
        Set<CustomerDto> customers = customerService.getAllCustomer();
        log.debug("SENDING RESPONSE FROM /v1/customer: {}", customers);
        return ResponseEntity
                .ok(customers);
    }

    @GetMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) {
        log.debug("NEW GET REQUEST TO /v1/customer/{}", customerId);
        CustomerDto customer = customerService.getCustomerById(customerId);
        log.debug("SENDING RESPONSE FROM /v1/customer/{}: {}", customerId, customer);
        return ResponseEntity
                .ok(customer);
    }
}
