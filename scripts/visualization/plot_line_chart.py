# ============================================================
# SCRIPT: plot_line_chart.py
# ============================================================
#
# OBJETIVO:
# Gerar gráfico de linha (tempo vs tamanho) para comparação
# de algoritmos.
#
# FUNCIONALIDADES:
# ✔ Média por tamanho
# ✔ Múltiplos algoritmos no mesmo gráfico
# ✔ Visualização clara de crescimento
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# graficos/line_chart.png
#
# ============================================================

import os
import pandas as pd
import matplotlib.pyplot as plt

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "graficos/line_chart.png"

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
        print(f"❌ Pasta não encontrada: {pasta}")
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
        except Exception as e:
            print(f"⚠️ Erro ao ler {arq}: {e}")

    if not dfs:
        return None

    return pd.concat(dfs, ignore_index=True)

# ============================================================
# GERAÇÃO DO GRÁFICO
# ============================================================

def plot_linhas(df):

    plt.figure(figsize=(10, 6))

    algoritmos = sorted(df["algoritmo"].unique())

    for alg in algoritmos:

        dados = df[df["algoritmo"] == alg]

        # Média por tamanho
        grupo = dados.groupby("tamanho")["tempo"].mean().reset_index()

        plt.plot(
            grupo["tamanho"],
            grupo["tempo"],
            marker='o',
            label=alg,
            color=CORES.get(alg, "#34495e")
        )

    plt.title("Tempo de Execução vs Tamanho")
    plt.xlabel("Tamanho da Entrada (n)")
    plt.ylabel("Tempo (ns)")
    plt.legend()
    plt.grid(True)

    os.makedirs("graficos", exist_ok=True)

    plt.tight_layout()
    plt.savefig(SAIDA)

    print(f"📊 {SAIDA}")

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🔍 Carregando dados...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Nenhum CSV encontrado")
        return

    df = carregar_dados(arquivos)

    if df is None or df.empty:
        print("❌ Nenhum dado válido")
        return

    print("📈 Gerando gráfico de linha...")

    plot_linhas(df)

    print("✅ Gráfico gerado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()