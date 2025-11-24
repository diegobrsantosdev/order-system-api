# Order System API

🇧🇷 [Versão em Português](#versão-em-português) | 🇺🇸 [English Version](#english-version)

---

## 🇧🇷 Versão em Português

### Descrição

API REST de um sistema de pedidos para gerenciamento de usuários, produtos, categorias e pedidos.  
Desenvolvida em **Java 17** com **Spring Boot**, **JPA/Hibernate** e camada de segurança robusta com **Spring Security** (autenticação e autorização).

O projeto segue boas práticas de arquitetura (camadas, DTOs, tratamento centralizado de exceções) e oferece cobertura de testes para os principais serviços e controllers com **JUnit 5** e **Mockito**.  
O projeto também foi conteinerizado com **Docker** e implantado na **AWS EC2**.

---

### Principais Tecnologias e Bibliotecas

- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- Spring Security
- Lombok
- Banco de Dados H2 (em memória)
- JUnit 5 e Mockito (testes)
- Maven
- Docker (conteinerização)
- AWS EC2 (deploy em nuvem)

---

### Estrutura do Projeto

- **entities:** Modelos de domínio - User, Order, Product, Category, OrderItem, Payment
- **dtos:** Data Transfer Objects para abstrair entidades nas requisições/respostas (inclui PasswordDTO para atualização de senha)
- **repositories:** Interfaces JPA para persistência e consultas
- **services:** Camada de lógica de negócio e validações
- **controllers:** Endpoints REST organizados por recurso
- **config:** Configurações globais e de segurança
- **exceptions:** Tratamento centralizado e personalizado de erros

---

### Postman example
![Postman response](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura30.png)


---

### Segurança

- Autenticação e autorização com Spring Security
- Senhas criptografadas (PasswordEncoder)
- Endpoints sensíveis protegidos
- Permissão diferenciada por perfil (em desenvolvimento)

![Senhas criptografadas](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura25.png)


---

### Relacionamentos das Entidades

- **User** 1 — * **Order** (Um usuário pode possuir vários pedidos)
- **Order** 1 — * **OrderItem** (Cada pedido tem vários itens)
- **OrderItem** * — 1 **Product** (Um item sempre aponta para um produto)
- **Order** 1 — 1 **Payment** (Pagamento exclusivo por pedido)
- **Product** * — * **Category** (Muitos para muitos)

![Diagrama do Domínio](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura31.png)


---

### Endpoints Principais

![Endpoints](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura29.png)


---

### Testes

- Cobertura para controllers e services (JUnit, Mockito)
- Testes unitários para User, Order, Product e Category
- Simulação de requests e verificações de fluxo de negócio

---

### Como Executar Localmente

#### 1️⃣ Via Java/Maven

1. Clone o repositório  
2. Requisitos: Java 17+ e Maven instalados  
3. Execute:  
   ```sh
   mvn spring-boot:run
   ```
4. Acesse o banco H2:  
   [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuário: `root`
   - Senha: `root`

#### Via Docker

1. Build da imagem:  
   ```sh
   docker build -t order-system-api .
   ```
2. Execute o container:  
   ```sh
   docker run -p 8080:8080 order-system-api
   ```
3. Acesse Swagger e endpoints:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> O projeto já foi deployado com sucesso na AWS EC2, provando sua escalabilidade.

---

### Observações & Aprendizados

- **Arquitetura e boas práticas:** Design em camadas, uso de DTOs para proteção/dessacoplamento.
- **Tratamento de exceções:** Centralizado, mensagens claras (incluindo erros de autenticação/autorização/validação).
- **Segurança:** Senhas criptografadas, atualização segura de senha via DTO, endpoints protegidos.
- **Relacionamentos complexos:** 1–*, *–1 e muitos para muitos com JPA/Hibernate, PKs compostas e Collections.
- **Testes:** Cobertura em services/controllers usando JUnit 5 e Mockito.
- **Conteinerização e deploy:** Dockerfile eficiente, configurações, deploy na AWS EC2.
- **DevOps:** Integração Spring Boot, Docker e AWS, resolução de problemas de ambiente e rede.

---

### Autor

- **Diego Melo Bezerra dos Santos**
- [diegobrsantosdev@gmail.com](mailto:diegobrsantosdev@gmail.com)
- [GitHub](https://github.com/diegobrsantosdev)

---

## 🇺🇸 English Version

### Description

REST API for an order management system enabling user, product, category, and order management.  
Developed in **Java 17** with **Spring Boot**, **JPA/Hibernate**, and a robust security layer using **Spring Security** (authentication and authorization).

The project follows best architecture practices (layers, DTOs, centralized exception handling) and provides services/controllers test coverage with **JUnit 5** and **Mockito**.  
It is also containerized with **Docker** and deployed to **AWS EC2**.

---

### Main Technologies and Libraries

- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- Spring Security
- Lombok
- H2 Database (in-memory)
- JUnit 5 & Mockito (testing)
- Maven
- Docker (containerization)
- AWS EC2 (cloud deployment)

---

### Project Structure

- **entities:** Domain models - User, Order, Product, Category, OrderItem, Payment
- **dtos:** Data Transfer Objects for request/response abstraction (includes PasswordDTO for password update)
- **repositories:** JPA interfaces for persistence and queries
- **services:** Business logic and validations layer
- **controllers:** Resource-based REST endpoints
- **config:** Global and security configurations
- **exceptions:** Centralized and customized error handling

---

### Postman example
![Postman response](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura30.png)


---

### Security

- Authentication and authorization with Spring Security
- Encrypted passwords (PasswordEncoder)
- Sensitive endpoints protected
- Role-based permissions (in development)

![Encrypted passwords](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura25.png)

---

### Entity Relationships

- **User** 1 — * **Order** (A user can have multiple orders)
- **Order** 1 — * **OrderItem** (Each order has multiple items)
- **OrderItem** * — 1 **Product** (An item always points to a product)
- **Order** 1 — 1 **Payment** (Exclusive payment per order)
- **Product** * — * **Category** (Many to many)

![Domain Diagram](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura31.png)

---

### Main Endpoints

![Endpoints](https://diegobrsantosdev.github.io/order-system-api/src/assets/captura29.png)

---

### Tests

- Coverage for controllers and services (JUnit, Mockito)
- Unit tests for User, Order, Product, and Category
- Request simulation and business logic flow checks

---

### How to Run Locally

#### 1️⃣ Using Java/Maven

1. Clone the repository  
2. Requirements: Java 17+ and Maven installed  
3. Run:  
   ```sh
   mvn spring-boot:run
   ```
4. Access the H2 database at:  
   [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `root`
   - Password: `root`

#### Using Docker

1. Build the image:  
   ```sh
   docker build -t order-system-api .
   ```
2. Run the container:  
   ```sh
   docker run -p 8080:8080 order-system-api
   ```
3. Access Swagger and endpoints at:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> The project has already been successfully deployed to AWS EC2, proving its scalability.

---

### Notes & Key Learnings

- **Architecture and best practices:** Layered design, DTOs for protection and decoupling.
- **Exception handling:** Centralized, clear messages (including authentication, authorization, and validation errors).
- **Security:** Password encryption, secure update via DTO, protected endpoints.
- **Complex relationships:** 1–*, *–1 and many-to-many with JPA/Hibernate, composite PKs, Collections.
- **Testing:** Service/controller coverage with JUnit 5 and Mockito.
- **Containerization & deploy:** Efficient Dockerfile, configurations, AWS EC2 deployment.
- **DevOps:** Spring Boot, Docker, and AWS integration, environment and network issue troubleshooting.

---

### Author

- **Diego Melo Bezerra dos Santos**
- [diegobrsantosdev@gmail.com](mailto:diegobrsantosdev@gmail.com)
- [GitHub](https://github.com/diegobrsantosdev)
