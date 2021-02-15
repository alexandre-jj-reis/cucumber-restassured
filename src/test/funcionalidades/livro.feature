# language: pt
@LivroTest
Funcionalidade: Recupera um livro por seu ISBN e pelo autor

  Cenario: O usuário liga para o serviço da web para obter um livro por seu ISBN
  	Dado que existe um livro com um isbn de 8533613407
  	Quando um usuário recupera o livro por isbn
  	Entao o código de status é 200
  	E a resposta inclui o seguinte
  	| totalItems 	 		| 1 					|
	| kind					| books#volumes			|
   E a resposta inclui o seguinte em qualquer ordem
	    | items.volumeInfo.title 					| Senhor Dos Aneis - Volume Unico |
		| items.volumeInfo.publisher 				| Martins Martins Fontes |
		| items.volumeInfo.pageCount 				| 1202 					|

  Cenario: O usuario faz uma chamada web para obrter um livro por seu etag
	Dado que existe um livro com o etag Je6fDwAAQBAJ
	Quando um usuário recupera o livro pelo seu etag
	Entao o código de status é 200
	E a resposta inclui os seguintes titulos
		| items.volumeInfo.title 					| O Labirinto Do Fauno |

