# 🧪 Metodologia

## 1. Visão Geral

A metodologia adotada neste projeto combina experimentação prática, automação e análise estatística para avaliar o desempenho de algoritmos de forma confiável e reprodutível.

O processo envolve:

1. Geração de dados de entrada
2. Execução dos algoritmos
3. Coleta de métricas
4. Armazenamento em CSV
5. Geração de gráficos
6. Análise estatística
7. Monitoramento contínuo via CI/CD

---

## 2. Geração de Dados

Os dados de entrada são gerados automaticamente utilizando diferentes tamanhos de vetor, como:

* 100
* 1.000
* 5.000
* 10.000

Para garantir reprodutibilidade, utiliza-se uma semente fixa na geração pseudoaleatória.

---

## 3. Execução dos Algoritmos

Cada algoritmo é executado sobre os mesmos dados de entrada, garantindo comparabilidade.

Os experimentos incluem:

* algoritmos de ordenação
* algoritmos de busca
* estruturas de dados
* algoritmos em grafos

---

## 4. Medição de Tempo

O tempo de execução é medido utilizando:

System.nanoTime()

Essa abordagem fornece alta precisão na medição.

---

## 5. Múltiplas Execuções

Para reduzir o impacto de variações externas, cada experimento pode ser executado múltiplas vezes.

A partir dessas execuções, são calculadas:

* média
* mínimo
* máximo
* desvio padrão

---

## 6. Armazenamento dos Resultados

Os resultados são armazenados em arquivos CSV com o seguinte formato:

tamanho,cenario,algoritmo,tempo

Exemplo:

1000,aleatorio,QuickSort,12000

---

## 7. Geração de Gráficos

São gerados automaticamente três tipos de gráficos:

### 📊 Gráfico linear

* comparação direta de tempos

### 📊 Gráfico logarítmico

* comparação entre ordens de grandeza

### 📊 Gráfico estatístico

* média com barras de erro (desvio padrão)

---

## 8. Cálculo de Speedup

O speedup é calculado para comparar algoritmos:

speedup = tempo_algoritmo / tempo_melhor

---

## 9. Pipeline Automatizado (CI/CD)

O projeto utiliza integração contínua para automatizar todo o processo experimental.

A cada execução do CI:

1. O projeto é compilado
2. Os benchmarks são executados
3. Os resultados são armazenados
4. O histórico é atualizado
5. Gráficos são gerados
6. Regressões são detectadas
7. Um relatório é criado
8. Um badge é atualizado

---

## 10. Detecção de Regressão

A regressão é detectada automaticamente comparando execuções consecutivas:

tempo_atual > 1.2 × tempo_anterior

Caso detectada:

* o CI pode falhar
* um alerta é gerado
* um comentário é feito no PR

---

## 11. Histórico de Execuções

Cada execução é armazenada e versionada, permitindo:

* análise temporal
* comparação entre execuções
* detecção de tendências

---

## 12. Dashboard Interativo

Os dados são disponibilizados em um dashboard web que permite:

* selecionar execuções
* comparar algoritmos
* visualizar ranking
* analisar speedup

---

## 13. Reprodutibilidade

Para garantir consistência dos resultados:

* ambiente de execução padronizado (CI)
* dados controlados
* pipeline automatizado

---

## 14. Síntese

A metodologia combina práticas acadêmicas e industriais, garantindo:

* rigor científico
* automação
* escalabilidade
* confiabilidade dos resultados
