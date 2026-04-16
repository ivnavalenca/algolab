# 🏗️ Arquitetura do Sistema

## 1. Visão Geral

O Algolab evoluiu de um projeto acadêmico para um sistema completo de análise de algoritmos, incorporando conceitos de engenharia de software, automação e observabilidade.

A arquitetura é composta por múltiplas camadas integradas:

* execução de benchmarks
* processamento de dados
* visualização
* automação via CI/CD

---

## 2. Componentes Principais

### 🔹 Núcleo de Benchmark (Java)

Responsável por:

* execução dos algoritmos
* coleta de métricas
* geração de dados (CSV)

Inclui:

* algoritmos de ordenação
* algoritmos de busca
* estruturas de dados
* algoritmos em grafos

---

### 🔹 Módulo de Visualização

Utiliza bibliotecas para geração de gráficos:

* gráficos lineares
* gráficos logarítmicos
* gráficos estatísticos

Os gráficos são exportados em formato PNG.

---

### 🔹 Scripts de Análise (Python)

Responsáveis por:

* comparação entre execuções
* detecção de regressão
* geração de gráficos para README
* manutenção do histórico

---

### 🔹 Dashboard Web

Interface interativa que permite:

* seleção de execuções
* comparação entre runs
* visualização de gráficos
* análise de ranking e speedup

---

### 🔹 Pipeline CI/CD

Executado automaticamente via GitHub Actions.

Responsável por:

* build do projeto
* execução dos benchmarks
* geração de resultados
* atualização do histórico
* detecção de regressões
* geração de relatórios
* atualização do README

---

## 3. Fluxo de Dados

O fluxo principal do sistema é:

1. Execução do benchmark
2. Geração de CSV
3. Processamento dos dados
4. Geração de gráficos
5. Armazenamento no repositório
6. Visualização no dashboard

---

## 4. Pipeline Automatizado

A cada execução do CI:

1. O código é compilado
2. Os benchmarks são executados
3. Um novo CSV é gerado
4. O histórico é atualizado
5. Gráficos são regenerados
6. A regressão é analisada
7. Um relatório é criado
8. Um badge é atualizado
9. Os resultados são versionados

---

## 5. Armazenamento

Os dados são armazenados em:

* arquivos CSV (resultados brutos)
* arquivos JSON (histórico)
* imagens PNG (gráficos)

---

## 6. Dashboard e Dados

O dashboard consome:

* CSVs históricos
* índice de execuções (index.json)

Permitindo análise dinâmica diretamente no navegador.

---

## 7. Observabilidade

O sistema implementa práticas de observabilidade:

* histórico de execuções
* monitoramento de desempenho
* detecção automática de regressão
* feedback contínuo

---

## 8. Escalabilidade

Embora seja executado localmente ou via CI, o sistema possui características que permitem evolução para arquiteturas mais complexas:

* separação de responsabilidades
* automação completa
* processamento assíncrono potencial

---

## 9. Integração

Os componentes estão integrados da seguinte forma:

* Java → gera dados
* Python → processa dados
* CI → automatiza execução
* Dashboard → apresenta resultados

---

## 10. Estrutura do Projeto

algolab/
├── src/                  # código Java
├── scripts/              # scripts Python
├── docs/                 # documentação e dashboard
├── resultados/           # dados gerados
└── .github/workflows/    # pipeline CI/CD

---

## 11. Evolução da Arquitetura

O projeto evoluiu em etapas:

1. Benchmark simples
2. Adição de gráficos
3. Inclusão de análise estatística
4. Automação com CI/CD
5. Dashboard interativo
6. Histórico e regressão automática

---

## 12. Síntese

A arquitetura do Algolab combina:

* execução eficiente
* análise robusta
* automação contínua
* visualização interativa

Resultando em um sistema completo de análise de algoritmos, com características próximas a ferramentas profissionais.
