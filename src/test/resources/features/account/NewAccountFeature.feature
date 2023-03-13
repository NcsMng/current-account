Feature: Create new account

  Scenario: New account creation
    Given customer exists in db for account creation
    When client calls endpoint for new account 'v1/account' with request '{"customerId": 1,"initialCredit": 1000}'
    Then status code from new account is 200
    And client gets created account with correct id
