package com.ncsgab.currentaccount.integration.account.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsgab.currentaccount.dto.request.NewAccountRequest;
import com.ncsgab.currentaccount.dto.response.AccountDto;
import com.ncsgab.currentaccount.model.Customer;
import com.ncsgab.currentaccount.repository.CustomerRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NewAccountFeatureSteps {
    @LocalServerPort
    private int randomServerPort;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;
    private Long customerId;
    private ResponseEntity<AccountDto> entity;

    public NewAccountFeatureSteps(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    @Given("customers exist in db")
    public void init_customer() {
        Customer customer = customerRepository.save(new Customer("Mario", "Rossi", new HashSet<>()));
        customerId = customer.getId();
    }

    @When("client calls endpoint {string} with request {string}")
    public void client_calls(String endpoint, String newAccountRequestJSON) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        NewAccountRequest newAccountRequest = objectMapper.readValue(newAccountRequestJSON, NewAccountRequest.class);
        entity = restTemplate
                .postForEntity(String.format("http://localhost:%s/%s", randomServerPort, endpoint), newAccountRequest, AccountDto.class);
    }

    @Then("entity status code is {int}")
    public void client_receives(int statusCode) {
        assertEquals(statusCode, entity.getStatusCode().value());
    }

    @And("client gets created account with correct id")
    public void client_receives_customer() {
        assertNotNull(entity.getBody());
        assertEquals(customerId, entity.getBody().id());
    }
}
