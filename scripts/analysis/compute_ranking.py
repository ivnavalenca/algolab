# ============================================================
# SCRIPT: compute_ranking.py
# ============================================================
#
# OBJETIVO:
# Gerar ranking final dos algoritmos com base em múltiplas métricas.
#
# MÉTRICAS UTILIZADAS:
# ✔ Tempo médio
# ✔ Coeficiente de crescimento (k)
# ✔ Qualidade da regressão (R²)
#
# RESULTADO:
# Score combinado para comparação justa entre algoritmos.
#
# ENTRADA:
# resultados/metrics.csv
# resultados/regression.csv
#
# SAÍDA:
# resultados/ranking.csv
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

METRICS_FILE = "resultados/metrics.csv"
REGRESSION_FILE = "resultados/regression.csv"
SAIDA = "resultados/ranking.csv"

# ============================================================
# NORMALIZAÇÃO
# ============================================================

def normalizar(coluna):
    """
    Normaliza valores entre 0 e 1.
    """
    return (coluna - coluna.min()) / (coluna.max() - coluna.min())

# ============================================================
# CÁLCULO DO RANKING
# ============================================================

def calcular_ranking(df_metrics, df_reg):

    # Média geral por algoritmo
    df_tempo = df_metrics.groupby("algoritmo")["media"].mean().reset_index()

    # Merge com regressão
    df = df_tempo.merge(df_reg, on="algoritmo")

    # Normalizações
    df["tempo_norm"] = normalizar(df["media"])
    df["k_norm"] = normalizar(df["k"])
    df["r2_norm"] = normalizar(df["r2"])

    # Score (quanto menor melhor)
    df["score"] = (
        df["tempo_norm"] * 0.5 +
        df["k_norm"] * 0.3 +
        (1 - df["r2_norm"]) * 0.2
    )

    # Ordena ranking
    df = df.sort_values(by="score")

    df["posicao"] = range(1, len(df) + 1)

    return df

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🏆 Gerando ranking...")

    if not os.path.exists(METRICS_FILE):
        print("❌ metrics.csv não encontrado")
        return

    if not os.path.exists(REGRESSION_FILE):
        print("❌ regression.csv não encontrado")
        return

    df_metrics = pd.read_csv(METRICS_FILE)
    df_reg = pd.read_csv(REGRESSION_FILE)

    if df_metrics.empty or df_reg.empty:
        print("❌ Dados insuficientes")
        return

    df_rank = calcular_ranking(df_metrics, df_reg)

    os.makedirs("resultados", exist_ok=True)

    df_rank.to_csv(SAIDA, index=False)

    print(f"📁 Ranking salvo em: {SAIDA}")
    print("✅ Ranking gerado com sucesso!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()