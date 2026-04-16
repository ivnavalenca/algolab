# 📊 Resultados e Análise

## 1. Visão Geral

Os resultados obtidos neste projeto são fruto da execução experimental de diferentes algoritmos, utilizando múltiplos tamanhos de entrada e coleta automatizada de métricas.

A análise considera:

* tempo de execução
* comportamento assintótico observado
* estabilidade (variabilidade)
* comparação entre algoritmos
* evolução ao longo do tempo

---

## 2. Resultados Experimentais

Os dados coletados são armazenados em arquivos CSV e utilizados para geração de gráficos.

Cada linha representa uma execução no formato:

tamanho,cenario,algoritmo,tempo

---

## 3. Análise dos Algoritmos de Ordenação

### 🔹 QuickSort

* Apresenta excelente desempenho médio
* Baixa variabilidade
* Melhor desempenho na maioria dos cenários

### 🔹 MergeSort

* Desempenho consistente
* Maior uso de memória
* Estável em diferentes entradas

### 🔹 BubbleSort

* Desempenho significativamente inferior
* Crescimento quadrático evidente
* Utilizado como referência de pior caso

---

## 4. Análise de Busca

### 🔹 Busca Linear

* Tempo cresce linearmente com o tamanho da entrada
* Simples, porém pouco eficiente em grandes volumes

### 🔹 Busca Binária

* Desempenho logarítmico
* Necessita dados ordenados
* Muito superior para grandes entradas

---

## 5. Estruturas de Dados

### 🔹 Vetor

* Inserção simples
* Boa performance em acesso direto

### 🔹 Tabela Hash

* Inserção eficiente
* Dependente da função de hash
* Pode sofrer colisões

---

## 6. Gráficos Gerados

O sistema gera automaticamente:

### 📈 Gráfico Linear

Permite observar o crescimento do tempo conforme o tamanho da entrada.

### 📈 Gráfico Logarítmico

Facilita a comparação entre algoritmos com diferentes ordens de grandeza.

### 📊 Gráfico Estatístico

Apresenta média e desvio padrão, permitindo avaliar estabilidade.

---

## 7. Análise de Variabilidade

A variabilidade é medida pelo desvio padrão:

* valores baixos indicam estabilidade
* valores altos indicam inconsistência

Essa análise é essencial para validar a confiabilidade dos resultados.

---

## 8. Speedup

O speedup permite comparar algoritmos em relação ao mais eficiente:

* valores próximos de 1 → desempenho similar ao melhor
* valores maiores → pior desempenho relativo

---

## 9. Evolução Temporal

Com o histórico de execuções, é possível observar:

* melhorias ao longo do tempo
* regressões de desempenho
* estabilidade do sistema

O gráfico temporal no README representa essa evolução.

---

## 10. Detecção de Regressão

O sistema identifica regressões automaticamente:

tempo_atual > 1.2 × tempo_anterior

Quando detectada:

* o CI pode falhar
* um alerta é gerado
* o PR recebe um relatório

---

## 11. Dashboard Interativo

O dashboard permite:

* comparar execuções
* visualizar gráficos interativos
* analisar ranking de algoritmos
* observar speedup

---

## 12. Discussão

Os resultados confirmam o comportamento esperado dos algoritmos conforme a teoria:

* algoritmos O(n log n) superam O(n²)
* algoritmos logarítmicos são mais eficientes em grandes entradas

Além disso, a análise experimental evidencia fatores práticos que não aparecem na análise teórica.

---

## 13. Síntese

Os resultados demonstram que:

* a abordagem experimental é essencial
* a automação aumenta a confiabilidade
* a análise contínua permite evolução do sistema

O projeto se consolida como uma ferramenta robusta para análise de desempenho de algoritmos.
