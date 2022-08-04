# pb-spring-store-microservices

Projeto com os serviços desenvolvidos na avaliação das Sprints 5 e 6 do Programa de Bolsas

## REQUISITOS DA SPRINT 5

### PRIMEIRO CARD:
I. Crie uma Api Pedido com as seguintes entidades:
- Pedido:
    - Id, Cpf, Itens, Total.
- Item:
    - Id, Nome, Data de Criação, Data de Validade, Valor, Descricao, Ofertas.
- Oferta:
    - Id, Nome, Data de Criação, Data de Validação, Data de Validade, Desconto, Descrição.

II. Haverá os seguintes endpoints:
- getAll-/api/pedido/  
   a. Campo “sort” para asc ou desc.  
   b. Campo “cpf” para filter.  
- getById-/api/pedido/{id}
- patch-/api/pedido/{id}
- patch-api/item/{id}
- post-/api/pedido/
- delete-/api/pedido/{id}

### SEGUNDO CARD:
I. Adicionar as validações:
- A data de criação da oferta não pode ser posterior a data de validade da oferta.
- Um item não pode ter uma oferta que tenha data de validação vencida.
- O id deverá ser gerado automaticamente.
- Deverá sempre ter um tratamento de resposta ou exceção proporcional a resposta ou exceção.
- Deverá utilizar handler.
- Quando um elemento requerido faltar, a mensagem deverá dizer qual exatamente falta.
- Validação de quantidades de números do CPF.
- Realize testes para o serviço com uma cobertura mínima de 70% para regras de negócio.

### TERCEIRO CARD:
Desenvolva outra SERVIÇO, um serviço de pagamentos, que irá receber apenas o id do pedido e o seu total, por meio de uma fila gerada sempre que houver um novo Pedido salvo na primeira Api.

Ao receber esses dados, eles devem ser salvos em uma tabela do BD, com um código único gerado automaticamente e a data do momento do evento.
