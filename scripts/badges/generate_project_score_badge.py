# ============================================================
# SCRIPT: generate_project_score_badge.py
# ============================================================
#
# OBJETIVO:
# Gerar badge representando a pontuação geral do projeto com
# base no ranking dos algoritmos.
#
# INTERPRETAÇÃO:
# ✔ Excelente → score >= 0.8
# ✔ Boa       → score >= 0.6
# ✔ Média     → score >= 0.4
# ✔ Fraca     → score < 0.4
#
# ENTRADA:
# resultados/ranking.csv
#
# SAÍDA:
# docs/assets/badge_project_score.svg
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

RANKING_FILE = "resultados/ranking.csv"
SAIDA = "docs/assets/badge_project_score.svg"

# ============================================================
# CLASSIFICAÇÃO
# ============================================================

def classificar(score):

    if score >= 0.8:
        return "Excelente", "#2ecc71"
    elif score >= 0.6:
        return "Boa", "#3498db"
    elif score >= 0.4:
        return "Média", "#f39c12"
    else:
        return "Fraca", "#e74c3c"

# ============================================================
# GERAÇÃO DO BADGE
# ============================================================

def gerar_badge(score, label, cor):

    return f"""
<svg xmlns="http://www.w3.org/2000/svg" width="220" height="20">
  <rect width="120" height="20" fill="#555"/>
  <rect x="120" width="100" height="20" fill="{cor}"/>
  <text x="60" y="14" fill="#fff" font-size="12" text-anchor="middle">
    Project Score
  </text>
  <text x="170" y="14" fill="#fff" font-size="12" text-anchor="middle">
    {score:.2f} ({label})
  </text>
</svg>
"""

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🏆 Gerando badge de score do projeto...")

    if not os.path.exists(RANKING_FILE):
        print("❌ ranking.csv não encontrado")
        return

    df = pd.read_csv(RANKING_FILE)

    if df.empty:
        print("❌ Dados inválidos")
        return

    # média dos scores
    score_medio = df["score"].mean()

    label, cor = classificar(score_medio)

    svg = gerar_badge(score_medio, label, cor)

    os.makedirs(os.path.dirname(SAIDA), exist_ok=True)

    with open(SAIDA, "w") as f:
        f.write(svg)

    print(f"📁 Badge salvo em: {SAIDA}")
    print("✅ Badge gerado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()