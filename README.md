# Twelve-Factor App Scaffold

Este repositório contém uma aplicação Spring Boot projetada seguindo rigorosamente a metodologia **Twelve-Factor App**. A aplicação atende aos requisitos do Lab Semana IV da disciplina de Arquitetura de Software.

## Início Rápido

### Pré-requisitos
- Git
- Docker e Docker Compose

### 1. Clonando e Executando a Aplicação
O processo de inicialização foi simplificado ao máximo. Basta clonar, preparar as variáveis de ambiente e subir os contêineres:

```bash
# 1. Clonar o repositório
git clone https://github.com/seu-usuario/12-fatores.git

# 2. Entrar na pasta do projeto
cd 12-fatores

# 3. Criar arquivo de ambiente local
cp .env.example .env

# 4. Subir a aplicação via Docker Compose
docker compose up --build
```

### 3. Verificação 
Com o contêiner rodando, faça um request para verificar a saúde da API:
```bash
curl http://localhost:8888/health
```

---

## Variáveis de Ambiente

As configurações da aplicação foram externalizadas. O sistema aceita as seguintes variáveis (veja `.env.example`):

- `PORT`: A porta em que o servidor web irá rodar (Ex: 8888).
- `LOG_LEVEL`: Nível de detalhamento dos logs (Ex: INFO, DEBUG).
- `DB_URL`: URL de conexão do banco de dados (Ex: jdbc:h2:mem:testdb).
- `DB_USERNAME`: Usuário do banco de dados.
- `DB_PASSWORD`: Senha do banco de dados.

---

## Tabela de Mapeamento dos Doze-Fatores

Abaixo estão os fatores implementados na aplicação, detalhando o status e onde foram aplicados:

| # | Nome do Fator | Status | Caminho/Referência de Implementação |
|---|---|---|---|
| I | **Base de Código** (Codebase) | Implementado | Repositório Git único com controle de versão rastreando a aplicação. |
| II | **Dependências** (Dependencies) | Implementado | Gerenciamento explícito e isolado de dependências no arquivo `pom.xml`. |
| III | **Configurações** (Config) | Implementado | Separação estrita de configurações lidas a partir de `.env` usando `application.yaml`. |
| IV | **Serviços de Apoio** (Backing services) | Implementado | O Banco de Dados (H2) é consumido via URL (`DB_URL`) acoplada na inicialização. |
| V | **Construa, lance, execute** (Build, release, run) | Implementado | Scripts independentes rodando em uma piline de CI/CD.|
| VI | **Processos** (Processes) | Implementado |A aplicação executa como um processo Stateless sem manter dados em memória entre requisições. Comportamento padrão do spring web MVC.|
| VII | **Vínculo de Portas** (Port binding) | Implementado | A porta do servidor embutido é injetada via variável no `application.yaml` (`server.port: ${PORT}`). |
| IX | **Descartabilidade** (Disposability) | Implementado | Implementação de *Graceful shutdown* nativo e intercepção de sinais (SIGTERM) via `GracefulShutdownHook.java`. |
| XI | **Logs** (Logs as Event Streams) | Implementado | Filtro customizado `LoggingConfig.java` enviando eventos sem formatação restrita direto para `stdout`. |
| VIII| **Concorrência** (Concurrency) | Adiado | A escalabilidade será gerida via  Kubernetes no futuro, delegando a criação de novas instâncias para o orquestrador.|
| X | **Paridade Dev/Prod** (Dev/prod parity) | Adiado |Atualmente rodamos H2 localmente. A implementação total requer os ambientes configurados.|
| XII | **Processos Administrativos** (Admin processes) | Adiado | Será incluído quando ferramentas como Flyway ou Liquibase forem aplicadas para rodar migrações pontuais em containers efêmeros separados. |

---