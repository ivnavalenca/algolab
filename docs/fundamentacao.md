# 🧠 Fundamentação Teórica

A análise de algoritmos estuda o desempenho de soluções computacionais
em função do tamanho da entrada (n), permitindo prever seu comportamento
à medida que os dados crescem.

------------------------------------------------------------------------

## 📌 Notação Big-O

A notação Big-O descreve o crescimento assintótico do tempo de execução,
ignorando constantes e termos de menor ordem.

### Classes principais:

-   **O(1)** → constante\
-   **O(log n)** → logarítmica\
-   **O(n)** → linear\
-   **O(n log n)** → quase linear\
-   **O(n²)** → quadrática

------------------------------------------------------------------------

## 📊 Exemplos

  Complexidade   Algoritmo
  -------------- ----------------
  O(1)           Acesso em Hash
  O(log n)       Busca Binária
  O(n)           Busca Linear
  O(n log n)     MergeSort
  O(n²)          BubbleSort

------------------------------------------------------------------------

## 🔎 Exemplo prático

Considere um vetor de tamanho n:

-   Busca linear → percorre todos os elementos → **O(n)**
-   Busca binária → divide o problema ao meio → **O(log n)**

Para n = 1.000.000:

-   Linear → até 1.000.000 operações\
-   Binária → cerca de 20 operações

------------------------------------------------------------------------

## 🎯 Importância

A análise permite:

-   escolher algoritmos eficientes\
-   projetar sistemas escaláveis\
-   reduzir custo computacional

------------------------------------------------------------------------

## 🔗 Teoria vs Prática

A teoria prevê crescimento, mas fatores como:

-   hardware\
-   JVM (JIT, otimizações)\
-   implementação

podem influenciar o tempo real.

Por isso, a análise teórica deve ser complementada por experimentos
empíricos.

------------------------------------------------------------------------

## 💡 Conclusão

A combinação entre análise teórica e validação prática é essencial para
compreender o comportamento real dos algoritmos.
