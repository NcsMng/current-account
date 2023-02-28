package com.ncsgab.currentaccount.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsgab.currentaccount.dto.mapper.CustomerMapper;
import com.ncsgab.currentaccount.repository.CustomerRepository;
import com.ncsgab.currentaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class IntegrationTestHelper extends TestHelper {

    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public AccountService accountService;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public CustomerMapper customerMapper;
    @Autowired
    public ObjectMapper mapper = new ObjectMapper();

}