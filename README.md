# Spring Bank

Spring Bank is a RESTful web service that provides banking services. It is built using Spring Boot, Spring Data JPA, PostgreSQL, and Docker.

## Features

- Create a new account
- Get account details
- Deposit money
- Withdraw money
- Transfer money between accounts

## Usage

### Prerequisites

- Docker and Docker Compose
- Java 17 or later
- Maven

### Running the application

1. Clone the repository
2. Run the command `docker-compose up` to start the PostgreSQL database
3. Run the application using the command `mvn spring-boot:run` or start the application in your IDE

### API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Swagger](https://swagger.io/)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
