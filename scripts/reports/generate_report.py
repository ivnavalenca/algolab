# ============================================================
# SCRIPT: generate_report.py
# ============================================================
#
# OBJETIVO:
# Gerar relatório consolidado do desempenho dos algoritmos.
#
# INCLUI:
# ✔ Métricas estatísticas
# ✔ Regressão (k e R²)
# ✔ Ranking final
#
# ENTRADA:
# resultados/metrics.csv
# resultados/regression.csv
# resultados/ranking.csv
#
# SAÍDA:
# docs/report.md
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

METRICS_FILE = "resultados/metrics.csv"
REGRESSION_FILE = "resultados/regression.csv"
RANKING_FILE = "resultados/ranking.csv"

SAIDA = "docs/report.md"

# ============================================================
# UTILITÁRIOS
# ============================================================

def carregar_csv(path):

    if not os.path.exists(path):
        print(f"❌ Arquivo não encontrado: {path}")
        return None

    df = pd.read_csv(path)

    if df.empty:
        print(f"❌ Arquivo vazio: {path}")
        return None

    return df

# ============================================================
# GERAÇÃO DO RELATÓRIO
# ============================================================

def gerar_markdown(df_metrics, df_reg, df_rank):

    linhas = []

    linhas.append("# 📊 Relatório de Análise de Algoritmos\n")

    # ========================================================
    # RANKING
    # ========================================================
    linhas.append("## 🏆 Ranking\n")

    for _, row in df_rank.iterrows():
        linhas.append(
            f"- {row['posicao']}º {row['algoritmo']} "
            f"(score: {row['score']:.3f})"
        )

    # ========================================================
    # MÉTRICAS
    # ========================================================
    linhas.append("\n## 📈 Métricas Estatísticas\n")

    for alg in df_metrics["algoritmo"].unique():

        dados = df_metrics[df_metrics["algoritmo"] == alg]

        media = dados["media"].mean()
        desvio = dados["desvio"].mean()

        linhas.append(
            f"- **{alg}** → média: {media:.2f}, desvio: {desvio:.2f}"
        )

    # ========================================================
    # REGRESSÃO
    # ========================================================
    linhas.append("\n## 📐 Regressão\n")

    for _, row in df_reg.iterrows():

        linhas.append(
            f"- **{row['algoritmo']}** → k={row['k']:.2f}, R²={row['r2']:.3f}"
        )

    return "\n".join(linhas)

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("📄 Gerando relatório...")

    df_metrics = carregar_csv(METRICS_FILE)
    df_reg = carregar_csv(REGRESSION_FILE)
    df_rank = carregar_csv(RANKING_FILE)

    if not all([df_metrics is not None, df_reg is not None, df_rank is not None]):
        print("❌ Dados incompletos")
        return

    markdown = gerar_markdown(df_metrics, df_reg, df_rank)

    os.makedirs(os.path.dirname(SAIDA), exist_ok=True)

    with open(SAIDA, "w") as f:
        f.write(markdown)

    print(f"📁 Relatório salvo em: {SAIDA}")
    print("✅ Relatório gerado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()