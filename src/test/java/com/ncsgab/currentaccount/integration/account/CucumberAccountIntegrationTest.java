package com.ncsgab.currentaccount.integration.account;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "../java/resources/features/account",
        glue = {"com.ncsgab.currentaccount.integration.account"})
public class CucumberAccountIntegrationTest {
}
