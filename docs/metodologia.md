# 🧪 Metodologia

Esta seção descreve os procedimentos adotados para a realização dos
experimentos, visando garantir resultados confiáveis, reprodutíveis e
comparáveis.

------------------------------------------------------------------------

## 📊 Estratégia Experimental

Os experimentos seguem as seguintes etapas:

1.  Geração dos dados de entrada (aleatório, ordenado e invertido)
2.  Execução dos algoritmos
3.  Medição do tempo de execução
4.  Repetição das execuções
5.  Cálculo da média dos tempos

------------------------------------------------------------------------

## 🔁 Número de Execuções

Cada algoritmo foi executado **10 vezes** para cada tamanho de entrada.

Essa abordagem é amplamente utilizada em experimentos computacionais
para reduzir variações e aumentar a confiabilidade dos resultados.

------------------------------------------------------------------------

## ⏱️ Medição de Tempo

A medição foi realizada utilizando:

``` java
System.nanoTime()
```

Essa função fornece medições com alta precisão, adequada para
experimentos de curta duração.

------------------------------------------------------------------------

## 📉 Tratamento dos Dados

Para reduzir ruídos experimentais:

-   Os tempos são armazenados em um vetor
-   Os valores são ordenados
-   O menor e o maior valor são descartados (remoção de outliers)
-   Calcula-se a média dos valores restantes

Essa técnica melhora a estabilidade dos resultados.

------------------------------------------------------------------------

## ⚙️ Influência da JVM

A execução em Java pode sofrer influência de fatores como:

-   JIT (Just-In-Time Compilation)
-   Garbage Collection
-   Otimizações em tempo de execução

Esses fatores podem causar variações nos tempos medidos.

------------------------------------------------------------------------

## 📈 Geração de Gráficos

Os gráficos foram gerados utilizando a biblioteca **JFreeChart**,
permitindo visualizar o comportamento dos algoritmos em função do
tamanho da entrada.

------------------------------------------------------------------------

## 🎯 Justificativa Metodológica

A combinação de:

-   múltiplas execuções\
-   remoção de outliers\
-   cálculo de média

é uma prática comum em experimentos científicos, pois:

-   reduz interferência do sistema operacional\
-   minimiza variações da JVM\
-   aumenta a confiabilidade dos dados

------------------------------------------------------------------------

## 💡 Observação Final

Os resultados devem ser interpretados como **tendências de
crescimento**, e não valores absolutos, devido às variações inerentes ao
ambiente de execução.
