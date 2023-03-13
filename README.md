
# Current Account API

## Spring Boot and ReactJS Application

This project provides APIs to fetch customers and to create current accounts for them.
Link to the front-end of the application : [current-account-fe](https://github.com/NcsMng/current-account-fe)


## Requirements

• The API will expose an endpoint which accepts the user information (customerID,
initialCredit).

• Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.

• Also, if initialCredit is not 0, a transaction will be sent to the new account.

• Another Endpoint will output the user information showing Name, Surname, balance, and
transactions of the accounts.

## APIs
* AccountAPI for creating a new account for an existing customer
* CustomerAPI for retrieving all customers

```html
GET /v1/customer/{customerId} - retrieves a customer
GET /v1/customer - retrieves all customers
POST /v1/account - creates a new account for existing customer
```


## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Mapstruct
- Lombok
- Restful API
- H2 in memory database  
- JUnit 5
- Cucumber

## Prerequisites

- Docker

## Pull & Run
Pull Docker images from GitHub Packages:
```bash
docker pull ghcr.io/ncsmng/current-account:latest
```
To run the pulled image locally using Docker:
```bash
docker run -p 8080:8080 ghcr.io/ncsmng/current-account:latest
```
## Api-Docs and Swagger
```bash
http://localhost:8080/v3/api-docs
```
```bash
http://localhost:8080/swagger-ui.html
```
