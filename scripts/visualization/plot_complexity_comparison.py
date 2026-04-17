# ============================================================
# SCRIPT: plot_complexity_comparison.py
# ============================================================

import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

PASTA = "resultados/historico"
SAIDA = "graficos/complexity_comparison.png"

def listar_csvs():
    if not os.path.exists(PASTA):
        return []
    return [os.path.join(PASTA, f) for f in os.listdir(PASTA) if f.endswith(".csv")]

def carregar():
    dfs = []
    for arq in listar_csvs():
        try:
            df = pd.read_csv(arq)
            if not df.empty:
                dfs.append(df)
        except:
            pass
    return pd.concat(dfs, ignore_index=True) if dfs else None

def plot(df):
    plt.figure(figsize=(10,6))

    for alg in df["algoritmo"].unique():
        dados = df[df["algoritmo"] == alg]
        grupo = dados.groupby("tamanho")["tempo"].mean().reset_index()

        n = grupo["tamanho"].values
        t = grupo["tempo"].values

        # normalização
        t_norm = t / max(t)

        # curvas teóricas
        linear = n / max(n)
        nlogn = (n * np.log2(n)) / max(n * np.log2(n))
        quadratic = (n**2) / max(n**2)

        plt.plot(n, t_norm, marker='o', label=f"{alg} (real)")

        plt.plot(n, linear, '--', alpha=0.4)
        plt.plot(n, nlogn, '--', alpha=0.4)
        plt.plot(n, quadratic, '--', alpha=0.4)

    plt.title("Comparação: Complexidade Real vs Teórica")
    plt.xlabel("n")
    plt.ylabel("Tempo normalizado")
    plt.legend()

    os.makedirs("graficos", exist_ok=True)
    plt.savefig(SAIDA)

    print(f"📊 {SAIDA}")

def main():
    df = carregar()
    if df is None:
        print("❌ Sem dados")
        return
    plot(df)

if __name__ == "__main__":
    main()