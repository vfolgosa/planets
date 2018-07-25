B2W - Desafio - Star Wars API
==================================================

Esta API foi desenvolvida para atender ao desafio da B2W

Arquitetura
-----------

Esta aplicação foi desenvolvida usando:

* Java 8
* Maven 4.0.0
* Spring Boot 1.4.4
* Apache Commons Lang 3
* MongoDB
* Docker

Começando
---------------

A arquitetura foi feita com dois containers, um da app e outro do mongodb e está configurada com o docker-compose, para rodar digite o comando docker-compose up --build

Métodos
------------------

* Listar todos os planetas: 
  - GET
  - /api/v1/planets/
  

* Incluir planeta: 
  - POST
  - /api/v1/planets/
  - Corpo da requisição:
```sh
{
	"name": "",
	"climate": "",
	"terrain": ""
}
```
 - Este serviço dispara um evento assíncrono que faz a requisição ao serviço externo para pegar a lista contendo os filmes
que o planeta aparece e atualiza o banco.

* Obter planeta por ID: 
  - GET
  - /api/v1/planets/{ID}

* Obter planeta por Nome: 
  - GET
  - /api/v1/planets/search/{NOME}

* Excluir planeta por ID: 
  - DELETE
  - /api/v1/planets/{ID}

