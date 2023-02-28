Feature: Get customer by id

  Scenario: Get customer with valid id
    Given customer exists in db
    When client calls endpoint 'v1/customer'
    Then response status code is 200
    And client gets customer with correct id
