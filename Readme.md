# RETORNOS API

- ## Objetivo
Esse software tem como propósito o estudo de APIs no IntelliJ com teste de retorno JSON no Thunder Client, usando as dependências Spring Web, Spring Data JPA, H2 Database e Validation.

- ## Como executar

Baixe o arquivo zip desse repositório no seu pc pessoal. Extraia os arquivos em uma pasta. No IntelliJ, vá em FILE, clique em OPEN e navegue até a pasta que você extraiu os arquivos. Selecione-a e dê OK.

Navegue até src -> main -> java e abra a pasta. Clique duas vezes em "RetornosApiApplication". Rode a aplicação com Shift-F10.

- ## Como testar
Após executar a aplicação, use Thunder Client no VS Code (baixe o plugin, caso não o tenha instalado). Clique em "New Request".

Selecione GET, POST, PUT ou DELETE. Recomendado iniciar com POST para salvar ao menos um item na memória. No campo para digitar a Url, digite a Url desejada. Em "Body", digite o texto JSON. Para finalizar, clique em "Send".

Use as rotas:

- Para salvar (POST): 
> /api/products
- Para buscar por id (GET): 
> api/products/{id}
- Para buscar por nome (GET): 
> /products/search?name=Television
- Para buscar tudo (GET):
> api/products
- Para atualizar um produto (PUT):
> api/products/{id}
- Para deletar um produto (DELETE) :
> api/products/{id}

- Exemplo de como criar com POST:

URL: http://localhost:8080/api/products

```
{
    "name": "Television",
    "price": 2500.00,
    "description": "A Samsung television",
    "inStockQuantity": 11,
    "category": "ELECTRONICS"
}
```


- ### Testando a validação do campo "name" (POST)

```
{
    "name": "TV",
    "price": 2500.00,
    "description": "A Samsung television",
    "inStockQuantity": 11,
    "category": "ELECTRONICS"
}
```

- ### Testando a validação do campo "price" (POST)

```
{
    "name": "Television",
    "price": 0.50,
    "description": "A Samsung television",
    "inStockQuantity": 11,
    "category": "ELECTRONICS"
}
```

- ### Testando a validação do campo "description" (POST)

```
{
    "name": "Television",
    "price": 2.500,
    "description": "",
    "inStockQuantity": 11,
    "category": "ELECTRONICS"
}
```

- ### Testando a validação do campo "inStockQuantity" (POST)

```
{
    "name": "Television",
    "price": 2.500,
    "description": "A Samsung television",
    "inStockQuantity": -2,
    "category": "ELECTRONICS"
}
```

- ### Testando a validação do campo "category" (POST)

```
{
    "name": "Television",
    "price": 1.500,
    "description": "",
    "inStockQuantity": 11,
    "category": "teste"
}
```