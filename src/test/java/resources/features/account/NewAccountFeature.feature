Feature: Create new account

  Scenario: New account creation
    Given customer exists in db
    When client calls endpoint 'v1/customer' with request '{customerId": 0","initialCredit": 1000}'
    Then response status code is 200
    And client gets created account with correct id
