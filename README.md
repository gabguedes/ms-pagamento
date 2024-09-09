## Microserviço se de Pagamentos

### Descrição
Projeto feito para disciplina de Microservice and Web Engineering do Curso de Sistemas de Informação.
A aplicação é uma API REST de cadastro de pagamentos.

### Técnologias Utilizadas
- ``InteliJ IDEA``
- ``Postman``
- ``Java 17``
- ``Spring Boot``
- ``Maven 4.0.0``
- ``JPA``
- ``H2 Database``
- ``Lombok``

#### Application properties
- A aplicação deverá ter os seguintes arquivos de configurações:
  - application.properties: definições.
  - application-test.properties: perfil de teste com o banco de dados H2.

### API Endpoints
#### User

Listar todos os Pagamentos.
```http
GET http://localhost:8080/pagamentos
```

Pagamento by Id
```http
GET http://localhost:8080/pagamentos/{id}
```

Create Pagamento

``` http
POST http://localhost:8080/pagamentos
```

Update Pagamento
```http
PUT http://localhost:8080/pagamentos/{id}
```

Delete Pagamento
```http
DELETE http://localhost:8080/pagamentos/{id}
```
