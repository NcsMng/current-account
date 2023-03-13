Feature: Get customer by id

  Scenario: Get customer with valid id
    Given customer exists in db
    When client calls customer by id endpoint 'v1/customer'
    Then response status code from customer by id is 200
    And client gets customer with correct id
