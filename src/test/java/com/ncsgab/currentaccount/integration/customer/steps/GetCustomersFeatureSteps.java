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

public class GetCustomersFeatureSteps {

    @LocalServerPort
    private int randomServerPort;
    private final CustomerRepository customerRepository;

    private ResponseEntity<CustomerDto[]> entity = null;
    private Long size = null;

    public GetCustomersFeatureSteps(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Given("customers exist in db")
    public void init_customer() {
        customerRepository.save(new Customer("Mario", "Rossi", new HashSet<>()));
        customerRepository.save(new Customer("Luigi", "Verdi", new HashSet<>()));
        size = customerRepository.count();
    }

    @When("client calls get customers endpoint {string}")
    public void client_calls(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();

        entity = restTemplate
                .getForEntity(String.format("http://localhost:%s/%s", randomServerPort, endpoint), CustomerDto[].class);
    }

    @Then("response status code is {int}")
    public void client_receives(int statusCode) {
        assertEquals(statusCode, entity.getStatusCode().value());
    }

    @And("client gets the correct number of customers")
    public void client_receives_customer() {
        assertNotNull(entity.getBody());
        assertEquals(size, Long.valueOf(entity.getBody().length));
    }
}
