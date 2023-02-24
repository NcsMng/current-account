package com.ncsgab.currentaccount.service;

import com.ncsgab.currentaccount.dto.response.CustomerDto;

import java.util.Set;

public interface CustomerService {
    CustomerDto getCustomerById(Long customerId);
    Set<CustomerDto> getAllCustomer();
}
