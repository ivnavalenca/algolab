# ============================================================
# SCRIPT: generate_quality_badge.py
# ============================================================
#
# OBJETIVO:
# Gerar badge indicando a qualidade da regressão dos algoritmos
# com base no coeficiente R².
#
# INTERPRETAÇÃO:
# ✔ Excelente  → R² >= 0.95
# ✔ Boa        → R² >= 0.90
# ✔ Aceitável  → R² >= 0.80
# ✔ Ruim       → R² < 0.80
#
# ENTRADA:
# resultados/regression.csv
#
# SAÍDA:
# docs/assets/badge_quality.svg
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

REGRESSION_FILE = "resultados/regression.csv"
SAIDA = "docs/assets/badge_quality.svg"

# ============================================================
# CLASSIFICAÇÃO
# ============================================================

def classificar(r2):

    if r2 >= 0.95:
        return "Excelente", "#2ecc71"
    elif r2 >= 0.90:
        return "Boa", "#3498db"
    elif r2 >= 0.80:
        return "Aceitável", "#f39c12"
    else:
        return "Ruim", "#e74c3c"

# ============================================================
# GERAÇÃO DO BADGE
# ============================================================

def gerar_badge(label, cor):

    return f"""
<svg xmlns="http://www.w3.org/2000/svg" width="200" height="20">
  <rect width="110" height="20" fill="#555"/>
  <rect x="110" width="90" height="20" fill="{cor}"/>
  <text x="55" y="14" fill="#fff" font-size="12" text-anchor="middle">
    Qualidade
  </text>
  <text x="155" y="14" fill="#fff" font-size="12" text-anchor="middle">
    {label}
  </text>
</svg>
"""

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🏷️ Gerando badge de qualidade...")

    if not os.path.exists(REGRESSION_FILE):
        print("❌ regression.csv não encontrado")
        return

    df = pd.read_csv(REGRESSION_FILE)

    if df.empty:
        print("❌ Dados inválidos")
        return

    # média do R² dos algoritmos
    r2_medio = df["r2"].mean()

    label, cor = classificar(r2_medio)

    svg = gerar_badge(label, cor)

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