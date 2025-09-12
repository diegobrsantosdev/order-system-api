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

![Postman response](./src/assets/captura30.png)

---

### Segurança

- Autenticação e autorização com Spring Security
- Senhas criptografadas (PasswordEncoder)
- Endpoints sensíveis protegidos
- Permissão diferenciada por perfil (em desenvolvimento)

![Senhas criptografadas](./src/assets/captura25.png)

---

### Relacionamentos das Entidades

- **User** 1 — * **Order** (Um usuário pode possuir vários pedidos)
- **Order** 1 — * **OrderItem** (Cada pedido tem vários itens)
- **OrderItem** * — 1 **Product** (Um item sempre aponta para um produto)
- **Order** 1 — 1 **Payment** (Pagamento exclusivo por pedido)
- **Product** * — * **Category** (Muitos para muitos)

![Diagrama do Domínio](./src/assets/captura31.png)

---

### Endpoints Principais

![Endpoints](./src/assets/captura29.png)

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
- [GitHub](https://github.com/seuusuario)

---

## 🇺🇸 English Version

### Description

REST API for an order system to manage users, products, categories, and orders.  
Developed with **Java 17**, **Spring Boot**, **JPA/Hibernate**, and a robust security layer using **Spring Security** (authentication and authorization).

Follows best architectural practices (layers, DTOs, centralized exception handling) and provides test coverage for main services and controllers using **JUnit 5** and **Mockito**.  
Containerized with **Docker** and deployed on **AWS EC2**, ensuring portability and scalability.

---

### Main Technologies and Libraries

- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- Spring Security
- Lombok
- H2 Database (in-memory)
- JUnit 5 and Mockito (testing)
- Maven
- Docker (containerization)
- AWS EC2 (cloud deployment)

---

### Project Structure

- **entities:** Domain models (User, Order, Product, Category, OrderItem, Payment)
- **dtos:** Data Transfer Objects for requests/responses (including PasswordDTO)
- **repositories:** JPA interfaces for database persistence
- **services:** Business logic and validations
- **controllers:** RESTful endpoints per resource
- **config:** Global and security configurations
- **exceptions:** Centralized and customized exception handling

![Postman response](./src/assets/captura30.png)

---

### Security

- Authentication and Authorization with Spring Security
- Passwords encrypted (PasswordEncoder)
- Protected sensitive endpoints
- Profile-based permissions (in development)

![Password Encryption](./src/assets/captura31.png)

---

### Entity Relationships

- **User** 1 — * **Order** (A user can have multiple orders)
- **Order** 1 — * **OrderItem** (Each order has multiple items)
- **OrderItem** * — 1 **Product** (An item always links to a product)
- **Order** 1 — 1 **Payment** (Unique payment per order)
- **Product** * — * **Category** (Many-to-many)

![Domain Diagram](./src/assets/captura25.png)

---

### Main Endpoints

![Endpoints](./src/assets/captura29.png)

---

### Tests

- Test coverage for controllers and services (JUnit, Mockito)
- Unit tests for User, Order, Product, and Category
- Simulation of requests and business flow validation

---

### Running Locally

#### Via Java/Maven

1. Clone the repository  
2. Requirements: Java 17+ and Maven  
3. Run:  
   ```sh
   mvn spring-boot:run
   ```
4. Access H2 database:  
   [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `root`
   - Password: `root`

#### Via Docker

1. Build the image:  
   ```sh
   docker build -t order-system-api .
   ```
2. Run the container:  
   ```sh
   docker run -p 8080:8080 order-system-api
   ```
3. Access Swagger and endpoints:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> The project is already deployed on AWS EC2, proving its scalability and production readiness.

---

### Observations & Learnings

- **Architecture & Best Practices:** Layered design, use of DTOs to protect entities and decouple layers.
- **Exception Handling:** Centralized error handling with clear messages (including auth/validation errors).
- **Security:** Password encryption, secured endpoints, secure password update via DTO.
- **Complex Relationships:** 1–*, *–1, many-to-many with JPA/Hibernate, composite PKs, Collections.
- **Tests:** Coverage for services/controllers (JUnit 5 and Mockito).
- **Containerization/Deployment:** Efficient Dockerfile, environment setup, AWS EC2 deployment.
- **DevOps:** Integration of Spring Boot, Docker, AWS, solving environment and network challenges.

---

### Author

- **Diego Melo Bezerra dos Santos**
- [diegobrsantosdev@gmail.com](mailto:diegobrsantosdev@gmail.com)
- [GitHub](https://github.com/seuusuario)
