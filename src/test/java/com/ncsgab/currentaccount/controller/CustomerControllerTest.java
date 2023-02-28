package com.ncsgab.currentaccount.controller;

import com.ncsgab.currentaccount.dto.response.CustomerDto;
import com.ncsgab.currentaccount.exception.CustomerNotFoundException;
import com.ncsgab.currentaccount.helpers.IntegrationTestHelper;
import com.ncsgab.currentaccount.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext
class CustomerControllerTest extends IntegrationTestHelper {

    @Test
    public void testGetCustomerById_whenCustomerExists_shouldReturnCustomerDto() throws Exception {

        Customer customer = customerRepository.save(customerSupplier.get());
        accountService.createAccount(requestFromCustomerIdAndCredit.apply(customer.getId(), 1_000));

        CustomerDto expected = customerMapper.entityToDto(
                customerRepository.findById(
                                Objects.requireNonNull(customer.getId()))
                        .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found for id: %s", customer.getId()))));

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "/" + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    public void testGetCustomerById_whenCustomerDoesNotExist_shouldReturnHttpNotFound() throws Exception {

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "-1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}