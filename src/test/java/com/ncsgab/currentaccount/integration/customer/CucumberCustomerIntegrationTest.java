package com.ncsgab.currentaccount.integration.customer;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "../java/resources/features/customer",
        glue = {"com.ncsgab.currentaccount.integration.customer"})
public class CucumberCustomerIntegrationTest {
}
