# ============================================================
# SCRIPT: plot_history_chart.py
# ============================================================
#
# OBJETIVO:
# Gerar gráfico de evolução do desempenho dos algoritmos ao
# longo das execuções (histórico).
#
# FUNCIONALIDADES:
# ✔ Mostra tendência temporal
# ✔ Detecta melhorias/regressões
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# graficos/history_chart.png
#
# ============================================================

import os
import pandas as pd
import matplotlib.pyplot as plt

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "graficos/history_chart.png"

# ============================================================
# UTILITÁRIOS
# ============================================================

def listar_csvs(pasta):
    if not os.path.exists(pasta):
        print(f"❌ Pasta não encontrada: {pasta}")
        return []

    arquivos = [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]

    return sorted(arquivos)  # ordena por timestamp


def extrair_timestamp(nome_arquivo):
    """
    Extrai timestamp do nome do CSV.
    Exemplo:
    ordenacao_2026-04-17_15-30-00.csv
    """
    base = os.path.basename(nome_arquivo)
    return base.split("_", 1)[-1].replace(".csv", "")


def carregar_execucoes(arquivos):

    dados = []

    for arq in arquivos:
        try:
            df = pd.read_csv(arq)

            if df.empty:
                continue

            timestamp = extrair_timestamp(arq)

            media = df.groupby("algoritmo")["tempo"].mean().reset_index()

            media["execucao"] = timestamp

            dados.append(media)

        except Exception as e:
            print(f"⚠️ Erro ao ler {arq}: {e}")

    if not dados:
        return None

    return pd.concat(dados, ignore_index=True)

# ============================================================
# GERAÇÃO DO GRÁFICO
# ============================================================

def plot(df):

    plt.figure(figsize=(12, 6))

    algoritmos = sorted(df["algoritmo"].unique())

    for alg in algoritmos:

        dados = df[df["algoritmo"] == alg]

        plt.plot(
            dados["execucao"],
            dados["tempo"],
            marker='o',
            label=alg
        )

    plt.title("Evolução do Tempo de Execução")
    plt.xlabel("Execução")
    plt.ylabel("Tempo Médio (ns)")
    plt.xticks(rotation=45)
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

    print("🔍 Carregando histórico...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Nenhum CSV encontrado")
        return

    df = carregar_execucoes(arquivos)

    if df is None or df.empty:
        print("❌ Nenhum dado válido")
        return

    print("📈 Gerando gráfico de histórico...")

    plot(df)

    print("✅ Gráfico de histórico gerado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()