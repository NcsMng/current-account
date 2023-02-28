package com.ncsgab.currentaccount.integration.customer.steps;

import com.ncsgab.currentaccount.dto.response.CustomerDto;
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

public class GetCustomerByIdFeatureSteps {
    @LocalServerPort
    private int randomServerPort;
    private final CustomerRepository customerRepository;

    private ResponseEntity<CustomerDto> entity = null;
    private Long id = null;

    public GetCustomerByIdFeatureSteps(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Given("customer exists in db")
    public void init_customer() {
        Customer customer = customerRepository.save(new Customer("Mario", "Rossi", new HashSet<>()));
        id = customer.getId();
    }

    @When("client calls endpoint {string}")
    public void client_calls(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        entity = restTemplate
                .getForEntity(String.format("http://localhost:%s/%s/%s", randomServerPort, endpoint, id), CustomerDto.class);
    }

    @Then("response status code is {int}")
    public void client_receives(int statusCode) {
        assertEquals(statusCode, entity.getStatusCode().value());
    }

    @And("client gets customer with correct id")
    public void client_receives_customer() {
        assertNotNull(entity.getBody());
        assertEquals(id, entity.getBody().id());
    }
}