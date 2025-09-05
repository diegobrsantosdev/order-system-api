# Order System API

[🇧🇷 Versão em Português](#versão-em-português) | [🇺🇸 English Version](#english-version)

---

## 🇧🇷 Versão em Português

### Descrição

Este projeto é uma API para gerenciamento de usuários e pedidos, desenvolvida em Java utilizando Spring Boot, JPA/Hibernate e arquitetura REST. O sistema permite cadastrar usuários, produtos, realizar pedidos, e acompanhar o status de cada ordem.

### Status

**Em desenvolvimento**

> Nota: Este projeto está em desenvolvimento. Novas funcionalidades, endpoints e relacionamentos de banco serão adicionados conforme o progresso.

### Tecnologias utilizadas

- Java 17+
- Spring Boot 3
- Spring Data JPA / Hibernate
- Banco de dados H2 (Memória)
- Lombok
- Maven

### Estrutura do projeto

- **entities** → Classes Java mapeadas para tabelas do banco (User, Order)
- **repositories** → Interfaces extendendo `JpaRepository` para operações CRUD
- **services** → Lógica de negócio
- **controllers** → Endpoints REST
- **resources** → Arquivos de configuração (`application.properties`)

### Endpoints

#### User
| Método | Endpoint      | Descrição                 |
|--------|---------------|---------------------------|
| GET    | `/users`      | Listar todos os usuários  |
| GET    | `/users/{id}` | Buscar usuário por ID     |
| GET    | `/orders`     | Listar todos os pedidos   |
| GET    | `/users/{id}` | Buscar pedido por ID      |

*(Novos endpoints para Orders serão adicionados em breve.)*

### Como executar

1. Acesse o console do banco H2:
   http://localhost:8080/h2-console
2. JDBC URL: jdbc:h2:mem:testdb
3. Usuário: root
4. Senha: root

### Desafios e Aprendizados

#### Serialização JSON e Loop Infinito
Durante o desenvolvimento, enfrentei um desafio comum em projetos com JPA: ao serializar uma entidade `Order`, a coleção de `OrderItem` criava um loop infinito por causa das referências bidirecionais entre as entidades (`Order`, `OrderItem`, e o ID composto `OrderItemPk`).
A solução foi utilizar a anotação `@JsonIgnore` no campo `order` dentro de `OrderItemPk`, além de ajustar os getters e setters para evitar que o Jackson serializasse as referências circulares.
Esse ajuste eliminou o loop e permitiu o retorno correto dos dados no formato JSON.

### Autor

Diego Melo Bezerra dos Santos  
Email: diegobrsantosdev@gmail.com  
GitHub: github.com/diegobrsantosdev

---

## 🇺🇸 English Version

### Description

This project is a REST API for managing users and orders, built with Java, Spring Boot, JPA/Hibernate, and REST architecture. The system allows you to register users, products, place orders, and track the status of each order.

### Status

**In Development**

> Note: This project is currently under development. More features, endpoints, and database relationships will be added as development progresses.

### Technologies Used

- Java 17+
- Spring Boot 3
- Spring Data JPA / Hibernate
- H2 In-Memory Database
- Lombok
- Maven

### Project Structure

- **entities** → Java classes mapped to database tables (User, Order)
- **repositories** → Interfaces extending `JpaRepository` for CRUD operations
- **services** → Business logic and rules
- **controllers** → REST endpoints
- **resources** → Configuration files (`application.properties`)

### Endpoints

#### User
| Method | Endpoint      | Description           |
|--------|---------------|-----------------------|
| GET    | `/users`      | Retrieve all users    |
| GET    | `/users/{id}` | Retrieve user by ID   |
| GET    | `/orders`     | Retrieve all orders   |
| GET    | `/users/{id}` | Retrieve order by ID  |

*(Additional endpoints for Orders will be added later.)*

### How to run

1. Access the H2 Database Console:
   http://localhost:8080/h2-console
2. JDBC URL: jdbc:h2:mem:testdb
3. Username: root
4. Password: root

### Challenges and Learnings

#### JSON Serialization and Infinite Loop
During development, I faced a common challenge with JPA projects: when serializing an `Order` entity, the collection of `OrderItem` created an infinite loop due to bidirectional references between entities (`Order`, `OrderItem`, and the composite ID `OrderItemPk`).
The solution was to use the `@JsonIgnore` annotation on the `order` field inside `OrderItemPk`, and adjust getters/setters to prevent Jackson from serializing circular references.
This adjustment eliminated the loop and allowed the correct JSON output.

### Author

Diego Melo Bezerra dos Santos  
Email: diegobrsantosdev@gmail.com  
GitHub: github.com/diegobrsantosdev

---