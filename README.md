# Picpay_v3

Realização do desafio picpay backend, buscando um implementação mais complexa/verossímil do exercício proposto, mas sem alterar o objetivo principal. Veja a descrição do desafio nesse link: https://github.com/PicPay/picpay-desafio-backend.

## Observações
- o sistema um consome um micro serviço que desenvolverei, mas que nesse projeto está sendo mockado;
- não há a implementação de testes na aplicação

## Banco de dados
Usei um banco relacional, mysql, em um container docker. A configuração do container se encontra em src/main/resources/docker/compose.yaml.

- credenciais:
	- user: root
	- password: 123
    - database: picpayv3

## Rotas
- UserController
	- GET :8080/user -> lista usuários cadastrados 
	- POST :8080/user -> cadastra usuário
		- body: UserRequestDTO
	- PUT :8080/user/{id} -> atualiza dados do usuário
		- body: UserRequestDTO
	- DELETE :8080/user/{id} -> deleta usuário
- WalletController
	- GET :8080/wallet/{user_id} -> retorna todas as carteiras do usuário
	- GET :8080/wallet/{id}/main -> retorna a carteira principal do usuário
	- POST :8080/wallet/{user_id} -> cria carteira para o usuário
		- body: WalletRequestDTO
	- DELETE :8080/wallet/{wallet_id} -> deleta carteira do usuário
- TransactionController
	- POST :8080/transaction -> cria transação entre carteiras
		- body: TransactionRequestDTO


## Data Transfer Objects
### UserRequestDTO
```json
{
	"name":"name",
	"document": "cpf/cnpj",
	"email": "name@gmail.com",
	"password": "password",
	"type": "PERSON"
}
```
### WalletRequestDTO
```json
{
	"desc":"description",
	"balance":200
}
```
### TransactionRequestDTO
```json
{
	"sender_id":"UUID",
	"receiver_id": "UUID",
	"amount": 200,
}
