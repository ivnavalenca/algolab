# 📈 Resultados

Esta seção apresenta os resultados obtidos a partir dos experimentos
realizados, analisando o comportamento dos algoritmos em função do
tamanho da entrada (n) e validando suas complexidades assintóticas.

------------------------------------------------------------------------

## 📁 Local dos Resultados

Os gráficos são gerados automaticamente na pasta:

    resultados/

------------------------------------------------------------------------

## 📊 Arquivos Gerados

-   busca_final.png
-   ordenacao_final.png
-   ordenacao_melhor.png
-   ordenacao_medio.png
-   ordenacao_pior.png
-   estruturas.png

------------------------------------------------------------------------

## 📊 Descrição dos Gráficos

Os gráficos representam o **tempo médio de execução (em nanossegundos)**
em função do tamanho da entrada (n).

Cada curva corresponde a um algoritmo, permitindo comparar seu
crescimento assintótico.

------------------------------------------------------------------------

## 📈 Análise Detalhada

### 🔍 Busca

-   Busca Linear → crescimento proporcional → **O(n)**
-   Busca Binária → crescimento logarítmico → **O(log n)**

**Interpretação:** A busca binária apresenta crescimento muito mais
lento, pois divide o espaço de busca a cada iteração.

------------------------------------------------------------------------

### 🔄 Ordenação

-   Algoritmos quadráticos → crescimento acelerado → **O(n²)**
-   Algoritmos eficientes → crescimento controlado → **O(n log n)**

**Interpretação:** Para grandes valores de n, algoritmos O(n²) tornam-se
inviáveis, enquanto O(n log n) continuam escaláveis.

------------------------------------------------------------------------

### 🟢 Melhor Caso

-   InsertionSort → comportamento próximo de **O(n)**
-   SelectionSort → mantém **O(n²)**

**Interpretação:** Algoritmos adaptativos se beneficiam de entradas já
ordenadas.

------------------------------------------------------------------------

### 🟡 Caso Médio

-   Comportamento consistente com a teoria
-   O(n²) começa a apresentar degradação

**Interpretação:** Representa cenários reais de uso.

------------------------------------------------------------------------

### 🔴 Pior Caso

-   Algoritmos O(n²) atingem custo máximo
-   QuickSort pode degradar dependendo do pivô
-   MergeSort mantém estabilidade → **O(n log n)**

**Interpretação:** Algoritmos com desempenho garantido são mais seguros
em aplicações críticas.

------------------------------------------------------------------------

### 🧱 Estruturas

-   Vetor → busca sequencial → **O(n)**
-   Hash → acesso direto → **O(1)** em média

**Interpretação:** Estruturas de dados eficientes reduzem drasticamente
o tempo de execução.

------------------------------------------------------------------------

## 📐 Validação Teórica

Os resultados experimentais confirmam a teoria de complexidade
assintótica, demonstrando que:

-   O(n²) cresce rapidamente
-   O(log n) cresce lentamente
-   O(n log n) apresenta equilíbrio entre desempenho e custo

------------------------------------------------------------------------

## ⚠️ Limitações

-   Resultados dependem do hardware e ambiente de execução
-   Influência da JVM (JIT, Garbage Collection)
-   Pequenas variações entre execuções

------------------------------------------------------------------------

## 💡 Conclusão

Os resultados demonstram que a análise assintótica é uma ferramenta
eficaz para prever o comportamento de algoritmos, sendo confirmada por
evidências experimentais.
