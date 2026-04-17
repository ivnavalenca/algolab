# ============================================================
# SCRIPT: plot_boxplot.py
# ============================================================
#
# OBJETIVO:
# Gerar boxplots a partir dos dados históricos dos algoritmos.
#
# FUNCIONALIDADES:
# ✔ Boxplot geral
# ✔ Boxplot por tamanho
# ✔ Boxplot científico (IC + teste t)
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# graficos/boxplot_*.png
#
# ============================================================

import os
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "graficos"

# Paleta consistente com Java
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


def aplicar_cores(box, labels):
    for patch, alg in zip(box["boxes"], labels):
        patch.set_facecolor(CORES.get(alg, "#34495e"))

# ============================================================
# BOXPLOT GERAL
# ============================================================

def plot_geral(df):

    plt.figure(figsize=(10, 6))

    grupos = []
    labels = []

    for alg in sorted(df["algoritmo"].unique()):
        dados = df[df["algoritmo"] == alg]["tempo"]

        if len(dados) > 1:
            grupos.append(dados)
            labels.append(alg)

    if not grupos:
        print("❌ Dados insuficientes")
        return

    box = plt.boxplot(grupos, labels=labels, patch_artist=True)
    aplicar_cores(box, labels)

    plt.title("Distribuição Geral de Tempo")
    plt.xlabel("Algoritmo")
    plt.ylabel("Tempo (ns)")
    plt.xticks(rotation=45)

    os.makedirs(SAIDA, exist_ok=True)
    caminho = os.path.join(SAIDA, "boxplot_geral.png")

    plt.tight_layout()
    plt.savefig(caminho)

    print(f"📊 {caminho}")

# ============================================================
# BOXPLOT POR TAMANHO
# ============================================================

def plot_por_tamanho(df):

    tamanhos = sorted(df["tamanho"].unique())

    for n in tamanhos:

        df_n = df[df["tamanho"] == n]

        grupos = []
        labels = []

        for alg in sorted(df_n["algoritmo"].unique()):
            dados = df_n[df_n["algoritmo"] == alg]["tempo"]

            if len(dados) > 1:
                grupos.append(dados)
                labels.append(alg)

        if not grupos:
            continue

        plt.figure(figsize=(10, 6))

        box = plt.boxplot(grupos, labels=labels, patch_artist=True)
        aplicar_cores(box, labels)

        plt.title(f"Distribuição (n={n})")
        plt.xlabel("Algoritmo")
        plt.ylabel("Tempo (ns)")
        plt.xticks(rotation=45)

        caminho = os.path.join(SAIDA, f"boxplot_n_{n}.png")

        plt.tight_layout()
        plt.savefig(caminho)

        print(f"📊 {caminho}")

# ============================================================
# BOXPLOT CIENTÍFICO
# ============================================================

def intervalo_confianca(dados):
    media = np.mean(dados)
    desvio = np.std(dados, ddof=1)
    erro = 1.96 * (desvio / np.sqrt(len(dados)))
    return media, erro


def plot_cientifico(df):

    plt.figure(figsize=(12, 7))

    grupos = []
    labels = []
    medias = []
    erros = []

    for alg in sorted(df["algoritmo"].unique()):
        dados = df[df["algoritmo"] == alg]["tempo"]

        if len(dados) > 1:
            grupos.append(dados)
            labels.append(alg)

            m, e = intervalo_confianca(dados)
            medias.append(m)
            erros.append(e)

    if not grupos:
        print("❌ Dados insuficientes")
        return

    box = plt.boxplot(grupos, labels=labels, patch_artist=True)
    aplicar_cores(box, labels)

    # Intervalo de confiança
    for i, (m, e) in enumerate(zip(medias, erros), start=1):
        plt.errorbar(i, m, yerr=e, fmt='o', color='black', capsize=5)

    plt.title("Boxplot com Intervalo de Confiança (95%)")
    plt.xlabel("Algoritmo")
    plt.ylabel("Tempo (ns)")
    plt.xticks(rotation=45)

    caminho = os.path.join(SAIDA, "boxplot_cientifico.png")

    plt.tight_layout()
    plt.savefig(caminho)

    print(f"📊 {caminho}")

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

    print("📊 Gerando boxplots...")

    plot_geral(df)
    plot_por_tamanho(df)
    plot_cientifico(df)

    print("✅ Boxplots gerados!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()