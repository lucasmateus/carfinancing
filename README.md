# 🚗 CarFinancing

Sistema de cálculo e gerenciamento de financiamentos de veículos desenvolvido em Spring Boot.

## 📋 Descrição

O CarFinancing é uma aplicação backend que oferece funcionalidades para calcular parcelas de financiamento de veículos, permitindo diferentes tipos de financiamento (interno e externo) com regras de negócio específicas. O sistema também permite salvar e consultar histórico de financiamentos realizados.

## ✨ Funcionalidades

- **Cálculo de Parcelas**: Calcula valores de parcelas baseado no valor do veículo e tipo de financiamento
- **Tipos de Financiamento**: Suporte a financiamento interno e externo com fatores diferentes
- **Validações**: Validação de dados de entrada e regras de negócio
- **Persistência**: Armazenamento de dados de clientes e financiamentos
- **Consulta**: API para consultar histórico de financiamentos
- **CORS**: Configurado para integração com frontend Angular

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.3** - Framework principal
- **Spring Web** - APIs REST
- **Spring Validation** - Validação de dados
- **Lombok** - Redução de boilerplate code
- **Maven** - Gerenciamento de dependências
- **Project Reactor** - Programação reativa (testes)

## 📦 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Git

## 🚀 Instalação e Execução

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd carfinancing
```

### 2. Compile o projeto
```bash
./mvnw clean compile
```

### 3. Execute a aplicação
```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8001`

## 📚 Como Usar

### Cálculo de Parcelas

**Endpoint:** `POST /api/installment/calculate`

**Exemplo de requisição:**
```json
{
  "financingType": "INTERNAL",
  "installmentCount": 24,
  "carValue": 50000.00
}
```

**Resposta:**
```json
{
  "financingType": "INTERNAL",
  "installmentCount": 24,
  "carValue": 50000.0,
  "monthlyInstallment": 2166.67
}
```

### Salvar Financiamento

**Endpoint:** `POST /api/installment/save`

**Exemplo de requisição:**
```json
{
  "financingType": "INTERNAL",
  "installmentCount": 24,
  "carValue": 50000.00,
  "name": "João Silva",
  "contact": "joao@email.com",
  "monthlyInstallment": 2166.67
}
```

### Consultar Histórico

**Endpoint:** `GET /api/installment/all`

Retorna todos os financiamentos salvos no sistema.

## ⚙️ Configurações

### application.properties

```properties
# Nome da aplicação
spring.application.name=carfinancing

# Fatores de financiamento
financing.factor.internal=1.04    # 4% de acréscimo
financing.factor.external=1.065   # 6.5% de acréscimo

# Porta da aplicação
server.port=8001
```

### Regras de Negócio

- **Financiamento Interno**: 12, 24, 36 ou 48 parcelas
- **Financiamento Externo**: 12, 24, 36, 48 ou 60 parcelas
- **Valor mínimo do veículo**: R$ 0,01
- **Fator interno**: 1,04 (4% de acréscimo)
- **Fator externo**: 1,065 (6,5% de acréscimo)

## 🏗️ Estrutura do Projeto

```
src/main/java/com/example/carfinancing/
├── CarfinancingApplication.java     # Classe principal
├── controller/
│   └── InstallmentController.java   # Controladores REST
├── service/
│   └── InstallmentService.java      # Lógica de negócio
├── model/
│   ├── BaseInstallment.java         # Modelo base
│   ├── InstallmentResult.java       # Resultado do cálculo
│   └── SavedInstallment.java        # Dados para persistência
├── enums/
│   └── FinancingType.java           # Tipos de financiamento
├── exception/
│   └── GlobalExceptionHandler.java  # Tratamento de exceções
├── config/
│   └── WebConfig.java               # Configurações web
└── util/
    └── FileStorageUtil.java         # Persistência em arquivo
```

## 🔧 Desenvolvimento

### Executar testes
```bash
./mvnw test
```

### Gerar JAR executável
```bash
./mvnw clean package
```

### Executar JAR
```bash
java -jar target/carfinancing-0.0.1-SNAPSHOT.jar
```

## 📝 Validações

O sistema implementa validações em diferentes níveis:

- **Bean Validation**: Validação automática de entrada
- **Regras de Negócio**: Validação de parcelas e valores
- **Tratamento de Exceções**: Respostas padronizadas de erro

### Exemplos de Validação

```java
@NotNull
private FinancingType financingType;

@Min(value = 12)
private int installmentCount;

@DecimalMin(value = "0.01")
private double carValue;

@NotBlank
@Email
private String contact;
```

## 🔒 Segurança

- Validação de entrada para prevenir injeção de dados maliciosos
- Tratamento de exceções para não expor informações sensíveis
- Configuração CORS para controle de acesso

## 📊 Persistência

Atualmente o sistema utiliza armazenamento em arquivo texto (`saved_installments.txt`) com formato estruturado:

```
Name: João Silva | Contact: joao@email.com | Type: INTERNAL | Installments: 24 | Value: 50000.00 | Monthly: 2166.67
```

## 🚧 Melhorias Futuras

- [ ] Migração para banco de dados relacional
- [ ] Implementação de autenticação e autorização
- [ ] Adição de logs estruturados
- [ ] Documentação com Swagger/OpenAPI
- [ ] Implementação de cache
- [ ] Métricas e monitoramento
- [ ] Testes unitários e de integração
- [ ] Dockerização da aplicação

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

Desenvolvido como projeto de demonstração Spring Boot.

## 📞 Suporte

Para dúvidas ou suporte, abra uma issue no repositório do projeto. 