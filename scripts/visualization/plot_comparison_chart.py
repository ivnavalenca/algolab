# ============================================================
# SCRIPT: plot_comparison_chart.py
# ============================================================
#
# OBJETIVO:
# Gerar gráfico comparativo entre algoritmos com base na média
# de tempo de execução.
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# graficos/comparison_chart.png
#
# ============================================================

import os
import pandas as pd
import matplotlib.pyplot as plt

PASTA = "resultados/historico"
SAIDA = "graficos/comparison_chart.png"

def listar_csvs(pasta):
    if not os.path.exists(pasta):
        return []

    return [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]

def carregar_dados(arquivos):
    dfs = []

    for arq in arquivos:
        try:
            df = pd.read_csv(arq)
            if not df.empty:
                dfs.append(df)
        except:
            pass

    if not dfs:
        return None

    return pd.concat(dfs, ignore_index=True)

def plot(df):

    plt.figure(figsize=(10, 6))

    grupo = df.groupby("algoritmo")["tempo"].mean().sort_values()

    plt.bar(grupo.index, grupo.values)

    plt.title("Tempo Médio por Algoritmo")
    plt.xlabel("Algoritmo")
    plt.ylabel("Tempo (ns)")
    plt.xticks(rotation=45)

    os.makedirs("graficos", exist_ok=True)

    plt.tight_layout()
    plt.savefig(SAIDA)

    print(f"📊 {SAIDA}")

def main():

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Sem dados")
        return

    df = carregar_dados(arquivos)

    if df is None:
        print("❌ Dados inválidos")
        return

    plot(df)

if __name__ == "__main__":
    main()