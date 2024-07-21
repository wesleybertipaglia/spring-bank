# Spring Bank

Spring Bank is a RESTful web service that provides banking services. It is built using Spring Boot, Spring Data JPA, PostgreSQL, and Docker.

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
- [ERD Diagram](#erd-diagram)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Running with Docker

The easiest way to run the application is with Docker.

```bash
docker-compose up -d
```

### Running with Maven

Alternatively, you can run the application with Maven.

#### Prerequisites

- Java 22 or later (it's compatible with Java 17, but you may need to update the `sourceCompatibility` and `targetCompatibility` in the `build.gradle` file)
- Maven 3.8.1 or later

```bash
mvn spring-boot:run
```

> The application will be available at [http://localhost:8080](http://localhost:8080).

> The Adminer will be available at [http://localhost:8081](http://localhost:8081).

## Features

- Create a new account
- Get account details
- Deposit money
- Withdraw money
- Transfer money between accounts

## ERD Diagram

The following Entity-Relationship Diagram (ERD) shows the relationships between the entities in the application:

```mermaid
classDiagram
    class User {
        uuid id
        string name
        string username
        roles role
        string password
        datetime created_at
        datetime updated_at
    }

    class Agency {
        uuid id        
        uuid bank_id
        int number
        string address
        datetime created_at
        datetime updated_at
    }
    
    class Account {
        uuid id
        uuid user_id
        uuid agency_id
        int number
        float balance      
        datetime created_at
        datetime updated_at
    }
    
    class Bank {
        uuid id
        string name
        string code        
        datetime created_at
        datetime updated_at
    }    

    class Transaction {
        uuid id
        uuid account_id
        float value
        transaction_type type
        string description
        datetime created_at
        datetime updated_at
    }

    User "1" -- "n" Account : owns
    Agency "1" -- "n" Account : has
    Bank "1" -- "n" Agency : has
    User "1" -- "n" Transaction : makes
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
