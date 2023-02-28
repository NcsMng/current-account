Feature: Get all customers

  Scenario: Get all customers successfully
    Given customers exist in db
    When client calls endpoint 'v1/customer'
    Then response status code is 200
    And client gets the correct number of customers
