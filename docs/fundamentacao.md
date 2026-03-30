# 📚 Fundamentação Teórica

## 1. Análise de Algoritmos

A análise de algoritmos é um campo fundamental da Ciência da Computação que busca avaliar o desempenho de algoritmos em termos de tempo e espaço. Tradicionalmente, essa análise é realizada por meio de notações assintóticas, como Big-O, que descrevem o comportamento do algoritmo conforme o tamanho da entrada cresce.

Entretanto, a análise teórica nem sempre reflete o comportamento prático, devido a fatores como arquitetura de hardware, cache, compilador e distribuição dos dados de entrada.

---

## 2. Análise Experimental

Este projeto adota uma abordagem experimental, baseada na execução real dos algoritmos e na coleta de métricas empíricas.

As principais métricas utilizadas incluem:

- Tempo de execução (em nanossegundos)
- Média das execuções
- Valor mínimo e máximo
- Desvio padrão

Essa abordagem permite observar o comportamento real dos algoritmos em diferentes cenários.

---

## 3. Variabilidade e Desvio Padrão

A execução de algoritmos pode apresentar variações devido a fatores externos, como o sistema operacional e concorrência de processos.

Para lidar com isso, são realizadas múltiplas execuções, permitindo calcular o desvio padrão, que indica a estabilidade dos resultados.

- Baixo desvio padrão → comportamento estável  
- Alto desvio padrão → comportamento instável  

---

## 4. Escala Logarítmica

Algoritmos com diferentes complexidades podem apresentar ordens de grandeza distintas.

Para melhor visualização, utiliza-se escala logarítmica, permitindo comparar algoritmos rápidos e lentos no mesmo gráfico.

---

## 5. Speedup

O speedup mede o desempenho relativo entre algoritmos:

Speedup = tempo_algoritmo / tempo_melhor

Valores próximos de 1 indicam desempenho próximo ao melhor algoritmo.

---

## 6. Reprodutibilidade

Para garantir consistência dos experimentos, o projeto utiliza:

- sementes fixas de geração de dados
- ambiente controlado de execução
- automação via CI/CD

---

## 7. Regressão de Performance

Regressão ocorre quando uma nova versão apresenta desempenho pior que a anterior.

Neste projeto, uma regressão é detectada quando:

tempo_atual > 1.2 × tempo_anterior

Esse critério permite identificar degradações significativas.

---

## 8. Observabilidade

O projeto incorpora conceitos modernos de observabilidade:

- coleta contínua de dados
- histórico de execuções
- visualização temporal
- alertas automáticos

Isso aproxima o sistema de práticas profissionais de engenharia.

---

## 9. CI/CD aplicado a desempenho

O uso de integração contínua permite:

- executar benchmarks automaticamente
- detectar regressões em Pull Requests
- gerar relatórios automáticos
- atualizar métricas públicas (badge)

---

## 10. Síntese

A combinação de análise teórica e experimental permite uma compreensão mais completa do comportamento dos algoritmos, tornando este projeto uma ferramenta robusta para estudo e avaliação de desempenho.