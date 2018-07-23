# planets

B2W - Desafio - Star Wars API
Esta API foi desenvolvida para atender ao desafio da B2W, para criação de uma API para um jogo com o tema Star Wars.

Arquitetura
Esta aplicação foi desenvolvida usando:

Java 8
Maven 4.0.0
Spring Boot 1.4.4
Apache Commons Lang 3
MongoDB

Começando
Para rodar a aplicação, basta executar a classe PlanetsApplication.java. Essa classe subirá uma versão do Tomcat e a aplicação estará disponível.

Métodos
Listar planetas:

GET
api/v1/planets/


Incluir planeta:

POST
api/v1/planets/
Corpo da requisição:
{
	"name": "",
	"climate": "",
	"terrain": ""
}
Obter planeta por ID:

GET
api/v1/planets/{ID}

Obter planeta por Nome:

GET
api/v1/planets/search/{NOME}

Excluir planeta por ID:

DELETE
api/v1/planets/{ID}
