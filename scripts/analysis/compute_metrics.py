# ============================================================
# SCRIPT: compute_metrics.py
# ============================================================
#
# OBJETIVO:
# Calcular métricas estatísticas a partir dos dados históricos
# dos algoritmos.
#
# MÉTRICAS:
# ✔ Média
# ✔ Desvio padrão
# ✔ Mediana
# ✔ Intervalo de confiança (95%)
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# resultados/metrics.csv
#
# ============================================================

import os
import pandas as pd
import numpy as np

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "resultados/metrics.csv"

# ============================================================
# LEITURA DE DADOS
# ============================================================

def listar_csvs(pasta):
    """
    Lista arquivos CSV do histórico.
    """
    if not os.path.exists(pasta):
        print(f"❌ Pasta não encontrada: {pasta}")
        return []

    return [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]


def carregar_dados(arquivos):
    """
    Carrega todos os CSVs e concatena.
    """
    dfs = []

    for arq in arquivos:
        try:
            df = pd.read_csv(arq)

            if df.empty:
                continue

            dfs.append(df)

        except Exception as e:
            print(f"⚠️ Erro ao ler {arq}: {e}")

    if not dfs:
        return None

    return pd.concat(dfs, ignore_index=True)

# ============================================================
# CÁLCULO DAS MÉTRICAS
# ============================================================

def calcular_metricas(df):
    """
    Calcula métricas por algoritmo e tamanho.
    """

    resultados = []

    grupos = df.groupby(["algoritmo", "tamanho"])

    for (algoritmo, tamanho), grupo in grupos:

        tempos = grupo["tempo"].values

        media = np.mean(tempos)
        desvio = np.std(tempos, ddof=1)
        mediana = np.median(tempos)

        # Intervalo de confiança (95%)
        erro = 1.96 * (desvio / np.sqrt(len(tempos)))

        resultados.append({
            "tamanho": tamanho,
            "algoritmo": algoritmo,
            "media": media,
            "desvio": desvio,
            "mediana": mediana,
            "ic_95": erro
        })

    return pd.DataFrame(resultados)

# ============================================================
# EXPORTAÇÃO
# ============================================================

def salvar(df):

    os.makedirs("resultados", exist_ok=True)

    df.to_csv(SAIDA, index=False)

    print(f"📁 Métricas salvas em: {SAIDA}")

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🔍 Carregando dados...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Nenhum CSV encontrado.")
        return

    df = carregar_dados(arquivos)

    if df is None or df.empty:
        print("❌ Nenhum dado válido.")
        return

    print("📊 Calculando métricas...")

    df_metricas = calcular_metricas(df)

    salvar(df_metricas)

    print("✅ Métricas geradas com sucesso!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()