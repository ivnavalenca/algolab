# 🧪 Experimentos

## 1. Objetivo

Os experimentos têm como objetivo avaliar empiricamente o desempenho de diferentes algoritmos em cenários variados, permitindo a comparação prática entre suas características.

---

## 2. Configuração Experimental

### Ambiente de Execução

Os experimentos são executados em ambiente controlado, utilizando:

* Máquina virtual (CI/CD)
* Execução automatizada via GitHub Actions
* Java 21

---

### Parâmetros

Os experimentos utilizam diferentes tamanhos de entrada:

* 100
* 1.000
* 5.000
* 10.000

---

### Cenários de Entrada

Os algoritmos são testados em diferentes cenários:

* dados aleatórios
* dados ordenados
* dados parcialmente ordenados

---

## 3. Experimentos Realizados

### 🔹 Ordenação

Algoritmos analisados:

* QuickSort
* MergeSort
* BubbleSort

Métricas coletadas:

* tempo de execução
* variabilidade

---

### 🔹 Busca

Algoritmos analisados:

* Busca Linear
* Busca Binária

Observação: a busca binária requer dados previamente ordenados.

---

### 🔹 Estruturas de Dados

Estruturas analisadas:

* Vetor
* Tabela Hash

Operação analisada:

* inserção

---

### 🔹 Grafos

Algoritmos analisados:

* BFS
* DFS

---

## 4. Procedimento

Para cada experimento:

1. Geração dos dados
2. Execução do algoritmo
3. Medição do tempo
4. Armazenamento em CSV
5. Repetição (quando aplicável)

---

## 5. Coleta de Dados

Os dados são armazenados no formato:

tamanho,cenario,algoritmo,tempo

---

## 6. Automação

Todos os experimentos são executados automaticamente via CI/CD, garantindo:

* consistência
* reprodutibilidade
* atualização contínua

---

## 7. Histórico

Cada execução gera um novo conjunto de dados, permitindo:

* comparação entre execuções
* análise temporal
* detecção de regressões

---

## 8. Síntese

Os experimentos foram projetados para fornecer uma base sólida para análise comparativa, garantindo confiabilidade e relevância dos resultados obtidos.
