# 📊 Análise de Algoritmos -- Experimentos Computacionais

**Disciplina:** Análise de Algoritmos\
**Professor:** Raphael Dourado\
**Aluna:** Ivna Valença de Oliveira

------------------------------------------------------------------------

## 🎯 Objetivo

Este projeto tem como objetivo analisar experimentalmente o desempenho
de algoritmos de busca e ordenação em diferentes cenários, validando
empiricamente suas complexidades.

------------------------------------------------------------------------

## 🧠 Metodologia

-   Execução múltipla (20 execuções)
-   Remoção de outliers
-   Uso de System.nanoTime()
-   Medição de tempo e memória
-   Testes em múltiplos cenários:
    -   Aleatório
    -   Ordenado
    -   Reverso
    -   Quase ordenado
-   Exportação para CSV
-   Geração automática de gráficos

------------------------------------------------------------------------

## 🏗️ Arquitetura

-   Strategy Pattern
-   Factory Pattern
-   Separação de responsabilidades
-   Pipeline automatizado
-   Gerenciamento de resultados por execução

------------------------------------------------------------------------

## 🔍 Algoritmos

### Busca

-   Linear --- O(n)
-   Binária --- O(log n)

### Ordenação

-   Bubble, Insertion, Selection --- O(n²)
-   Merge, Quick --- O(n log n)
-   Arrays.sort() --- baseline

------------------------------------------------------------------------

## ▶️ Execução

``` bash
gradle run --args="benchmark"
```

------------------------------------------------------------------------

## 📁 Estrutura de Resultados

    resultados/
     └── run_YYYY-MM-DD_HH-MM-SS/
          ├── ordenacao.csv
          ├── ordenacao_tempo.png
          ├── ordenacao_memoria.png
          ├── busca.csv
          ├── busca_grafico.png

------------------------------------------------------------------------

## 🤖 Interpretação Automática

### 📊 Crescimento

-   Algoritmos O(n²) crescem rapidamente
-   Algoritmos O(n log n) escalam melhor

### ⚡ Busca

-   Linear cresce com n
-   Binária mantém desempenho estável

### 📉 Cenários

-   Ordenado → melhor caso para alguns algoritmos
-   Reverso → pior caso
-   Quase ordenado → cenário realista

### 💾 Memória

-   MergeSort consome mais memória
-   QuickSort é mais eficiente (in-place)

------------------------------------------------------------------------

## 🧠 Conclusão

Os resultados confirmam a teoria e demonstram que o desempenho depende
não apenas do tamanho da entrada, mas também do cenário e da estrutura
dos dados.

------------------------------------------------------------------------

## 📎 Licença

Projeto acadêmico para fins educacionais.
