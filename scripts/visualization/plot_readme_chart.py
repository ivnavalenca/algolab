# ============================================================
# SCRIPT: plot_readme_chart.py
# ============================================================
#
# OBJETIVO:
# Gerar gráfico simplificado e visualmente otimizado para uso
# em README.md e GitHub Pages.
#
# CARACTERÍSTICAS:
# ✔ Visual limpo
# ✔ Poucos elementos (sem poluição visual)
# ✔ Destaque claro entre algoritmos
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# docs/assets/readme_chart.png
#
# ============================================================

import os
import pandas as pd
import matplotlib.pyplot as plt

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "docs/assets/readme_chart.png"

CORES = {
    "BubbleSort": "#e74c3c",
    "InsertionSort": "#f39c12",
    "SelectionSort": "#9b59b6",
    "MergeSort": "#2ecc71",
    "QuickSort": "#3498db"
}

# ============================================================
# UTILITÁRIOS
# ============================================================

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

# ============================================================
# GERAÇÃO DO GRÁFICO
# ============================================================

def plot(df):

    plt.figure(figsize=(8, 5))

    algoritmos = sorted(df["algoritmo"].unique())

    for alg in algoritmos:

        dados = df[df["algoritmo"] == alg]

        grupo = dados.groupby("tamanho")["tempo"].mean().reset_index()

        plt.plot(
            grupo["tamanho"],
            grupo["tempo"],
            label=alg,
            linewidth=2,
            color=CORES.get(alg, "#34495e")
        )

    plt.title("Comparação de Algoritmos")
    plt.xlabel("n")
    plt.ylabel("Tempo")
    plt.legend()
    
    # REMOVE GRID PARA FICAR MAIS LIMPO
    plt.grid(False)

    os.makedirs(os.path.dirname(SAIDA), exist_ok=True)

    plt.tight_layout()
    plt.savefig(SAIDA, dpi=150)

    print(f"📊 {SAIDA}")

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🔍 Gerando gráfico para README...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Sem dados")
        return

    df = carregar_dados(arquivos)

    if df is None or df.empty:
        print("❌ Dados inválidos")
        return

    plot(df)

    print("✅ Gráfico do README atualizado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()