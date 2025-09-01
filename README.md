# Order System API

**Status:** In Development 

This is a REST API for managing users and orders, built with **Spring Boot 3**, **Spring Data JPA**, **H2 Database**, and **Lombok**.

>  Note: This project is currently under development. More features, endpoints, and database relationships will be added as development progresses.

---

## Technologies Used

- Java 17+
- Spring Boot 3
- Spring Data JPA
- H2 In-Memory Database
- Lombok
- Maven

---

## Project Structure

- **entities** → Java classes mapped to database tables (User, Order)
- **repositories** → Interfaces extending `JpaRepository` for CRUD operations
- **services** → Business logic and rules
- **controllers** → REST endpoints
- **resources** → Configuration files (`application.properties`)

---

## Endpoints

### User

| Method | Endpoint      | Description                 |
|--------|---------------|-----------------------------|
| GET    | `/users`      | Retrieve all users          |
| GET    | `/users/{id}` | Retrieve user by ID         |
| GET   | `/orders`      | Retrieve all orders        |
| GET    | `/users/{id}` | Retrieve order by ID   |

*(Additional endpoints for Orders will be added later.)*

---

## Getting Started

1. Access the H2 Database Console:

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: root

Password: root

---

Author

Diego Melo Bezerra dos Santos

Email: diegobrsantosdev@gmail.com

GitHub: github.com/diegobrsantosdev
