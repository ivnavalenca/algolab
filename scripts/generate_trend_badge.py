# ============================================================
# 📈 BADGE: TENDÊNCIA DE DESEMPENHO
# ============================================================
#
# RESPONSABILIDADES:
# - Ler timeline histórica dos algoritmos
# - Comparar primeira vs última execução
# - Detectar melhoria ou regressão
# - Gerar badge dinâmico (JSON)
#
# ============================================================

import json
import os

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
ARQUIVO_ENTRADA = "docs/resultados/ordenacao_timeline.json"
SAIDA = "docs/resultados/badge_trend.json"

# ============================================================
# 📈 ANÁLISE DE TENDÊNCIA
# ============================================================
def gerar_badge():
    """
    Analisa evolução dos algoritmos ao longo do tempo.
    """

    if not os.path.exists(ARQUIVO_ENTRADA):
        print("Arquivo de timeline não encontrado.")
        return

    # --------------------------------------------------------
    # CARREGA DADOS
    # --------------------------------------------------------
    with open(ARQUIVO_ENTRADA) as f:
        data = json.load(f)

    melhorias = 0
    pioras = 0

    # --------------------------------------------------------
    # ANALISA CADA ALGORITMO
    # --------------------------------------------------------
    for algoritmo, pontos in data.items():

        if len(pontos) < 2:
            continue

        inicio = pontos[0]["tempo"]
        fim = pontos[-1]["tempo"]

        if fim < inicio:
            melhorias += 1
        elif fim > inicio:
            pioras += 1

    # --------------------------------------------------------
    # DEFINE TENDÊNCIA GLOBAL
    # --------------------------------------------------------
    if melhorias > pioras:
        tendencia = "melhorando"
        cor = "brightgreen"
    elif pioras > melhorias:
        tendencia = "piorando"
        cor = "red"
    else:
        tendencia = "estavel"
        cor = "yellow"

    # --------------------------------------------------------
    # GERA BADGE
    # --------------------------------------------------------
    badge = {
        "schemaVersion": 1,
        "label": "tendencia",
        "message": tendencia,
        "color": cor
    }

    # --------------------------------------------------------
    # SALVA JSON
    # --------------------------------------------------------
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(badge, f)

    print("Badge de tendência gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_badge()