# ============================================================
# SCRIPT: generate_summary_badge.py
# ============================================================
#
# OBJETIVO:
# Gerar badge resumo do projeto com base no ranking dos algoritmos.
#
# INFORMAÇÕES:
# ✔ Melhor algoritmo
# ✔ Score geral
#
# ENTRADA:
# resultados/ranking.csv
#
# SAÍDA:
# docs/assets/badge_summary.svg
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

RANKING_FILE = "resultados/ranking.csv"
SAIDA = "docs/assets/badge_summary.svg"

# ============================================================
# GERAÇÃO DO BADGE
# ============================================================

def gerar_badge(algoritmo, score):

    return f"""
<svg xmlns="http://www.w3.org/2000/svg" width="250" height="20">
  <rect width="120" height="20" fill="#555"/>
  <rect x="120" width="130" height="20" fill="#4c1"/>
  <text x="60" y="14" fill="#fff" font-size="12" text-anchor="middle">
    Melhor Algoritmo
  </text>
  <text x="185" y="14" fill="#fff" font-size="12" text-anchor="middle">
    {algoritmo} ({score:.2f})
  </text>
</svg>
"""

# ============================================================
# PIPELINE
# ============================================================

def main():

    print("🏷️ Gerando badge resumo...")

    if not os.path.exists(RANKING_FILE):
        print("❌ ranking.csv não encontrado")
        return

    df = pd.read_csv(RANKING_FILE)

    if df.empty:
        print("❌ Dados inválidos")
        return

    melhor = df.iloc[0]

    svg = gerar_badge(melhor["algoritmo"], melhor["score"])

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