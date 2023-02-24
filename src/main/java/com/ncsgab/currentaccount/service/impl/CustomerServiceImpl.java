package com.ncsgab.currentaccount.service.impl;

import com.ncsgab.currentaccount.dto.mapper.CustomerMapper;
import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.exception.CustomerNotFoundException;
import com.ncsgab.currentaccount.repository.CustomerRepository;
import com.ncsgab.currentaccount.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::entityToDto)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found for user id: %s", customerId)));
    }

    @Override
    public Set<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::entityToDto)
                .collect(Collectors.toSet());
    }
}
