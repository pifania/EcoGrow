# EcoGrow — Como Rodar

## O que você precisa ter instalado
- Java 17+ → https://adoptium.net
- Maven → https://maven.apache.org/download.cgi
- MySQL → https://dev.mysql.com/downloads/installer

---

## Passo 1 — Configurar o MySQL

Abra o MySQL e execute:
```sql
CREATE DATABASE ecogrow;
```

Se a sua senha do MySQL **não for "root"**, abra o arquivo:
`src/main/resources/application.properties`

E mude a linha:
```
spring.datasource.password=root
```
Para a sua senha.

---

## Passo 2 — Rodar o Backend

Abra o terminal na pasta do projeto e execute:
```bash
mvn spring-boot:run
```

Aguarde aparecer: `Started EcoGrowApplication`

---

## Passo 3 — Abrir o Site

Com o backend rodando, acesse no navegador:
```
http://localhost:8080
```

---

## O que funciona

- Página inicial com apresentação do projeto
- Cadastro de usuário
- Login
- Dashboard com métricas
- Cadastrar plantas
- Irrigar plantas (aumenta umidade em 20%)
- Remover plantas
- Tudo salvo no banco MySQL

---

## Endpoints da API (para apresentação)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /api/usuarios/cadastrar | Cadastra usuário |
| POST | /api/usuarios/login | Login |
| GET | /api/plantas/usuario/{id} | Lista plantas do usuário |
| POST | /api/plantas | Cadastra planta |
| POST | /api/plantas/{id}/irrigar | Irriga planta |
| DELETE | /api/plantas/{id} | Remove planta |
