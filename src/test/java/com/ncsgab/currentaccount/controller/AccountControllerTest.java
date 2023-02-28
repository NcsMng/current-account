package com.ncsgab.currentaccount.controller;

import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.helpers.IntegrationTestHelper;
import com.ncsgab.currentaccount.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@RunWith(SpringRunner.class)
class AccountControllerTest extends IntegrationTestHelper {

    @Test
    public void testNewAccount_whenCustomerExists_shouldCreateAccountAndReturnAccountDto() throws Exception {
        Customer mockedCustomer = customerRepository.save(customerSupplier.get());
        NewAccountRequest request = requestFromCustomerIdAndCredit.apply(mockedCustomer.getId(), 1_000);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.balance", is(1_000)))
                .andExpect(jsonPath("$.customer.id", is(mockedCustomer.getId()), Long.TYPE))
                .andExpect(jsonPath("$.customer.name", is(mockedCustomer.getName())))
                .andExpect(jsonPath("$.customer.surname", is(mockedCustomer.getSurname())))
                .andExpect(jsonPath("$.transactions", hasSize(1)));
    }

    @Test
    public void testNewAccount_whenCustomerDoesNotExist_shouldReturn404NotFound() throws Exception {
        NewAccountRequest request = requestFromCustomerIdAndCredit.apply(-1L, 1_000);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testNewAccount_whenRequestHasLessThanZeroInitialCredit_shouldReturn400BadRequest() throws Exception {
        NewAccountRequest request = requestFromCustomerIdAndCredit.apply(0L, -1_000);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNewAccount_whenRequestHasNoCustomerId_shouldReturn400BadRequest() throws Exception {
        NewAccountRequest request = requestFromCustomerIdAndCredit.apply(null, 1_000);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}