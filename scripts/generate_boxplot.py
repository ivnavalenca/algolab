# ============================================================
# SCRIPT: generate_boxplot.py
# ============================================================
#
# OBJETIVO:
# Gerar boxplots a partir dos resultados históricos dos algoritmos.
#
# FUNCIONALIDADES:
# ✔ Boxplot geral (todos os algoritmos)
# ✔ Boxplot por tamanho (n)
# ✔ Boxplot científico (intervalo de confiança + teste t)
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
from scipy.stats import ttest_ind

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "graficos"

CORES = {
    "BubbleSort": "#e74c3c",
    "InsertionSort": "#f39c12",
    "SelectionSort": "#9b59b6",
    "MergeSort": "#2ecc71",
    "QuickSort": "#3498db",
    "BuscaLinear": "#e67e22",
    "BuscaBinaria": "#1abc9c"
}

# ============================================================
# UTILITÁRIOS
# ============================================================

def listar_csvs(pasta):
    """
    Lista todos os arquivos CSV da pasta.
    """
    if not os.path.exists(pasta):
        print(f"⚠️ Pasta não encontrada: {pasta}")
        return []

    return [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]


def carregar_dados(arquivos):
    """
    Carrega e concatena todos os CSVs.
    """
    dfs = []

    for arq in arquivos:
        try:
            df = pd.read_csv(arq)

            if df.empty:
                print(f"⚠️ CSV vazio: {arq}")
                continue

            dfs.append(df)

        except Exception as e:
            print(f"❌ Erro ao ler {arq}: {e}")

    if not dfs:
        return None

    return pd.concat(dfs, ignore_index=True)


def aplicar_cores(boxplot, algoritmos):
    """
    Aplica cores personalizadas aos boxplots.
    """
    for patch, alg in zip(boxplot['boxes'], algoritmos):
        cor = CORES.get(alg, "#34495e")
        patch.set_facecolor(cor)


# ============================================================
# ESTATÍSTICA
# ============================================================

def intervalo_confianca(dados):
    """
    Calcula intervalo de confiança (95%).
    """
    media = np.mean(dados)
    desvio = np.std(dados, ddof=1)
    n = len(dados)

    erro = 1.96 * (desvio / np.sqrt(n))

    return media, erro


def significancia(a, b):
    """
    Teste t (Welch) entre dois grupos.
    """
    stat, p = ttest_ind(a, b, equal_var=False)
    return p


# ============================================================
# BOXPLOT GERAL
# ============================================================

def gerar_boxplot_geral(df):
    """
    Gera boxplot geral (todos os algoritmos).
    """

    plt.figure(figsize=(10, 6))

    grupos = []
    labels = []

    for alg in sorted(df["algoritmo"].unique()):
        dados = df[df["algoritmo"] == alg]["tempo"]

        if len(dados) > 1:
            grupos.append(dados)
            labels.append(alg)

    if not grupos:
        print("❌ Dados insuficientes para boxplot geral")
        return

    box = plt.boxplot(grupos, labels=labels, patch_artist=True)
    aplicar_cores(box, labels)

    plt.title("Distribuição Geral de Tempo por Algoritmo")
    plt.xlabel("Algoritmo")
    plt.ylabel("Tempo (ns)")
    plt.xticks(rotation=45)

    os.makedirs(SAIDA, exist_ok=True)

    caminho = os.path.join(SAIDA, "boxplot_geral.png")

    plt.tight_layout()
    plt.savefig(caminho)

    print(f"✅ Boxplot geral gerado: {caminho}")


# ============================================================
# BOXPLOT POR TAMANHO
# ============================================================

def gerar_boxplot_por_tamanho(df):
    """
    Gera boxplots separados por tamanho (n).
    """

    tamanhos = sorted(df["tamanho"].unique())

    for n in tamanhos:

        df_n = df[df["tamanho"] == n]

        if df_n.empty:
            continue

        plt.figure(figsize=(10, 6))

        grupos = []
        labels = []

        for alg in sorted(df_n["algoritmo"].unique()):
            dados = df_n[df_n["algoritmo"] == alg]["tempo"]

            if len(dados) > 1:
                grupos.append(dados)
                labels.append(alg)

        if not grupos:
            continue

        box = plt.boxplot(grupos, labels=labels, patch_artist=True)
        aplicar_cores(box, labels)

        plt.title(f"Distribuição de Tempo (n={n})")
        plt.xlabel("Algoritmo")
        plt.ylabel("Tempo (ns)")
        plt.xticks(rotation=45)

        os.makedirs(SAIDA, exist_ok=True)

        caminho = os.path.join(SAIDA, f"boxplot_n_{n}.png")

        plt.tight_layout()
        plt.savefig(caminho)

        print(f"✅ Boxplot gerado: {caminho}")


# ============================================================
# BOXPLOT CIENTÍFICO
# ============================================================

def gerar_boxplot_cientifico(df):
    """
    Gera boxplot com intervalo de confiança e significância.
    """

    plt.figure(figsize=(12, 7))

    algoritmos = sorted(df["algoritmo"].unique())

    grupos = []
    medias = []
    erros = []

    for alg in algoritmos:
        dados = df[df["algoritmo"] == alg]["tempo"]

        if len(dados) > 1:
            grupos.append(dados)

            media, erro = intervalo_confianca(dados)
            medias.append(media)
            erros.append(erro)

    if not grupos:
        print("❌ Dados insuficientes para gráfico científico")
        return

    box = plt.boxplot(grupos, labels=algoritmos, patch_artist=True)
    aplicar_cores(box, algoritmos)

    # Intervalo de confiança
    for i, (media, erro) in enumerate(zip(medias, erros), start=1):
        plt.errorbar(i, media, yerr=erro, fmt='o', color='black', capsize=5)

    # Teste t (exemplo: primeiros dois algoritmos)
    if len(grupos) >= 2:
        p = significancia(grupos[0], grupos[1])

        texto = "ns"
        if p < 0.05:
            texto = "*"

        y = max([max(g) for g in grupos]) * 1.05
        plt.text(1.5, y, texto, ha='center', fontsize=14)

    plt.title("Boxplot com Intervalo de Confiança (95%)")
    plt.xlabel("Algoritmo")
    plt.ylabel("Tempo (ns)")
    plt.xticks(rotation=45)

    os.makedirs(SAIDA, exist_ok=True)

    caminho = os.path.join(SAIDA, "boxplot_cientifico.png")

    plt.tight_layout()
    plt.savefig(caminho)

    print(f"✅ Boxplot científico gerado: {caminho}")


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================

def gerar_boxplots():
    """
    Executa todo o pipeline de geração de gráficos.
    """

    print("🔍 Carregando histórico...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Nenhum CSV encontrado.")
        print("👉 Execute os experimentos primeiro.")
        return

    print(f"📂 {len(arquivos)} arquivos encontrados.")

    df = carregar_dados(arquivos)

    if df is None or df.empty:
        print("❌ Nenhum dado válido.")
        return

    if not {"algoritmo", "tempo", "tamanho"}.issubset(df.columns):
        print("❌ CSV inválido.")
        return

    print("📊 Gerando gráficos...")

    gerar_boxplot_geral(df)
    gerar_boxplot_por_tamanho(df)
    gerar_boxplot_cientifico(df)

    print("🎉 Todos os boxplots gerados com sucesso!")


# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    gerar_boxplots()