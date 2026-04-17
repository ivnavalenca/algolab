# ============================================================
# SCRIPT: generate_trend_badge.py
# ============================================================
#
# OBJETIVO:
# Gerar badge indicando tendência do desempenho dos algoritmos
# com base nas execuções mais recentes.
#
# INTERPRETAÇÃO:
# ✔ improving  → desempenho melhorando
# ✔ regressing → desempenho piorando
# ✔ stable     → sem mudança significativa
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# docs/assets/badge_trend.svg
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "docs/assets/badge_trend.svg"

# ============================================================
# UTILITÁRIOS
# ============================================================

def listar_csvs(pasta):
    if not os.path.exists(pasta):
        return []

    arquivos = [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]

    return sorted(arquivos)


def carregar_execucao(arq):
    try:
        df = pd.read_csv(arq)
        if df.empty:
            return None
        return df
    except:
        return None

# ============================================================
# ANÁLISE DE TENDÊNCIA
# ============================================================

def analisar_tendencia(arquivos):

    if len(arquivos) < 2:
        return "stable", "#95a5a6"

    arq_ant = arquivos[-2]
    arq_atual = arquivos[-1]

    df_ant = carregar_execucao(arq_ant)
    df_atual = carregar_execucao(arq_atual)

    if df_ant is None or df_atual is None:
        return "stable", "#95a5a6"

    media_ant = df_ant.groupby("algoritmo")["tempo"].mean()
    media_atual = df_atual.groupby("algoritmo")["tempo"].mean()

    algoritmos = set(media_ant.index).intersection(set(media_atual.index))

    variacoes = []

    for alg in algoritmos:
        t_ant = media_ant[alg]
        t_atual = media_atual[alg]

        if t_ant > 0:
            variacoes.append((t_atual - t_ant) / t_ant)

    if not variacoes:
        return "stable", "#95a5a6"

    media_variacao = sum(variacoes) / len(variacoes)

    if media_variacao < -0.05:
        return "improving", "#2ecc71"
    elif media_variacao > 0.05:
        return "regressing", "#e74c3c"
    else:
        return "stable", "#95a5a6"

# ============================================================
# GERAÇÃO DO BADGE
# ============================================================

def gerar_badge(status, cor):

    return f"""
<svg xmlns="http://www.w3.org/2000/svg" width="180" height="20">
  <rect width="100" height="20" fill="#555"/>
  <rect x="100" width="80" height="20" fill="{cor}"/>
  <text x="50" y="14" fill="#fff" font-size="12" text-anchor="middle">
    Trend
  </text>
  <text x="140" y="14" fill="#fff" font-size="12" text-anchor="middle">
    {status}
  </text>
</svg>
"""

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("📈 Gerando badge de tendência...")

    arquivos = listar_csvs(PASTA)

    status, cor = analisar_tendencia(arquivos)

    svg = gerar_badge(status, cor)

    os.makedirs(os.path.dirname(SAIDA), exist_ok=True)

    with open(SAIDA, "w") as f:
        f.write(svg)

    print(f"📁 Badge salvo em: {SAIDA}")
    print("✅ Badge de tendência gerado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()