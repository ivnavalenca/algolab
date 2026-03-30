# 📊 Experimentos

Esta seção descreve detalhadamente os experimentos realizados, com o
objetivo de analisar empiricamente o comportamento dos algoritmos
estudados e validar suas complexidades assintóticas.

------------------------------------------------------------------------

## 🔍 Experimento 1: Busca

### 🎯 Objetivo

Comparar o desempenho de algoritmos de busca em função do tamanho da
entrada.

### ⚙️ Algoritmos analisados

-   Busca Linear → **O(n)**
-   Busca Binária → **O(log n)**

### 🧪 Metodologia

-   Vetores aleatórios foram gerados
-   A chave buscada foi selecionada dentro do vetor
-   Para a busca binária, o vetor foi previamente ordenado

### 📊 Análise esperada

A busca linear percorre todos os elementos, enquanto a busca binária
reduz o espaço de busca pela metade a cada iteração.

### 💡 Interpretação esperada

Com o aumento de n, a busca binária apresenta crescimento muito inferior
à busca linear.

------------------------------------------------------------------------

## 🔄 Experimento 2: Ordenação

### 🎯 Objetivo

Comparar algoritmos de ordenação com diferentes complexidades.

### ⚙️ Algoritmos O(n²)

-   BubbleSort
-   InsertionSort
-   SelectionSort

### ⚙️ Algoritmos O(n log n)

-   MergeSort
-   QuickSort

### 🧪 Metodologia

-   Vetores aleatórios foram utilizados
-   Cada algoritmo recebeu a mesma entrada (uso de clone)

### 📊 Análise esperada

Algoritmos quadráticos apresentam crescimento acelerado, enquanto
algoritmos O(n log n) são mais eficientes para grandes entradas.

### 💡 Interpretação esperada

Para valores grandes de n, algoritmos O(n²) tornam-se inviáveis.

------------------------------------------------------------------------

## 🟢 Experimento 3: Ordenação por Casos

### 🎯 Objetivo

Analisar o impacto da estrutura da entrada no desempenho dos algoritmos.

### ⚙️ Tipos de entrada

-   Melhor caso → vetor ordenado
-   Caso médio → vetor aleatório
-   Pior caso → vetor invertido

### 📊 Análise esperada

-   InsertionSort melhora significativamente no melhor caso
-   SelectionSort mantém comportamento constante O(n²)

### 💡 Interpretação esperada

Algoritmos adaptativos se beneficiam de entradas ordenadas.

------------------------------------------------------------------------

## 🧱 Experimento 4: Estruturas de Dados

### 🎯 Objetivo

Comparar o impacto da estrutura de dados no tempo de acesso.

### ⚙️ Estruturas analisadas

-   Vetor → busca linear (**O(n)**)
-   Tabela Hash → acesso direto (**O(1)** em média)

### 📊 Análise esperada

A tabela hash apresenta desempenho superior devido ao acesso direto por
função de dispersão.

### 💡 Interpretação esperada

A escolha da estrutura de dados pode impactar mais que a escolha do
algoritmo.

------------------------------------------------------------------------

## 🎯 Síntese dos Experimentos

Os experimentos foram projetados para:

-   validar empiricamente a teoria de complexidade\
-   comparar algoritmos sob diferentes condições\
-   evidenciar a importância da escolha do algoritmo e da estrutura de
    dados

------------------------------------------------------------------------

## 🔗 Relação Teoria vs Prática

Os resultados experimentais permitem observar, na prática, o
comportamento previsto pela análise assintótica, reforçando sua
importância no desenvolvimento de soluções eficientes.

------------------------------------------------------------------------

## 💡 Observação Final

Os resultados devem ser interpretados como tendências de crescimento, e
não valores absolutos.
