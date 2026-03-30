import pandas as pd
import matplotlib.pyplot as plt

# carregar CSV
df = pd.read_csv("resultados/ordenacao.csv")

# criar gráfico
for algoritmo in df["algoritmo"].unique():
    sub = df[df["algoritmo"] == algoritmo]
    plt.plot(sub["tamanho"], sub["tempo"], label=algoritmo)

plt.xlabel("Tamanho")
plt.ylabel("Tempo")
plt.title("Desempenho de Ordenação")
plt.legend()

# salvar
plt.savefig("resultados/graficos/ordenacao.png")
print("Gráfico atualizado!")