# EcoLogística

Sistema de otimização de rotas para frota elétrica utilizando a heurística **Nearest Neighbor (Vizinho Mais Próximo)**, com persistência em memória, uso de **Streams/Lambdas**, **exceções personalizadas**, menu interativo em terminal e suporte a **janelas de tempo** no roteamento.

## Contexto

A startup fictícia **EcoLogística** opera uma frota de vans 100% elétricas.  
Como os veículos possuem autonomia limitada, o sistema precisa calcular rotas mais eficientes para evitar deslocamentos desnecessários e otimizar as entregas do dia.

Além da distância geográfica, o sistema também considera **restrições temporais**, como entregas com horário limite, priorizando encomendas urgentes quando necessário.

## Funcionalidades

O sistema permite:

- gerar/carregar dados iniciais para teste
- listar depósitos, veículos, entregas e rotas
- executar o algoritmo de roteamento
- exibir relatórios e estatísticas
- encerrar o sistema pelo menu

## Requisitos implementados

### Requisitos principais
- menu interativo no terminal
- persistência em memória com `Map` e repositórios
- uso de **Streams e Lambdas**
- uso de **exceções personalizadas**
- algoritmo de roteamento com **ciclo fechado** (retorno ao depósito)
- geração de dados de teste via menu

### Desafio adicional
- entregas com **janelas de tempo**
- priorização de entregas urgentes
- uso de **Deque** para reorganizar a rota planejada
- lançamento de `RotaInviavelException` quando não for possível atender todas as janelas

## Estrutura do projeto

```text
src/com/ecologistica
├── app
│   └── Main.java
├── domain
│   ├── Deposito.java
│   ├── Entrega.java
│   ├── Ponto.java
│   ├── Rota.java
│   ├── Veiculo.java
│   └── enums
├── exception
├── repository
├── service
└── ui
