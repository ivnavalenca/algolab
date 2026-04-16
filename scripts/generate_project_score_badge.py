# ============================================================
# 🧮 BADGE: SCORE GERAL DO PROJETO
# ============================================================
#
# RESPONSABILIDADES:
# - Ler métricas (k e R²)
# - Ler ranking (score dos algoritmos)
# - Calcular score global do projeto
# - Gerar badge dinâmico (JSON)
#
# ============================================================

import json
import os

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
ARQUIVO_METRICS = "docs/resultados/ordenacao_metrics.json"
ARQUIVO_RANKING = "docs/resultados/ordenacao_ranking.json"
SAIDA = "docs/resultados/badge_score.json"

# ============================================================
# 🧮 CÁLCULO DO SCORE GLOBAL
# ============================================================
def gerar_score():
    """
    Calcula um score geral baseado em:
    - complexidade média (k)
    - qualidade média (R²)
    - score médio dos algoritmos
    """

    # --------------------------------------------------------
    # VERIFICA EXISTÊNCIA DOS ARQUIVOS
    # --------------------------------------------------------
    if not os.path.exists(ARQUIVO_METRICS):
        print("Arquivo de métricas não encontrado.")
        return

    if not os.path.exists(ARQUIVO_RANKING):
        print("Arquivo de ranking não encontrado.")
        return

    # --------------------------------------------------------
    # CARREGA DADOS
    # --------------------------------------------------------
    with open(ARQUIVO_METRICS) as f:
        metrics = json.load(f)

    with open(ARQUIVO_RANKING) as f:
        ranking = json.load(f)

    # --------------------------------------------------------
    # CALCULA MÉDIAS
    # --------------------------------------------------------
    k_medio = sum(v["k"] for v in metrics.values()) / len(metrics)
    r2_medio = sum(v["r2"] for v in metrics.values()) / len(metrics)

    score_algoritmos = sum(v["score"] for v in ranking.values()) / len(ranking)

    # --------------------------------------------------------
    # FÓRMULA DO SCORE
    # --------------------------------------------------------
    score = (
        (1 / (1 + k_medio)) * 0.3 +  # menor k → melhor
        r2_medio * 0.4 +             # maior R² → melhor
        score_algoritmos * 0.3       # ranking → desempenho geral
    )

    # escala 0–100
    score_final = round(score * 100, 1)

    # --------------------------------------------------------
    # DEFINE COR DO BADGE
    # --------------------------------------------------------
    if score_final > 80:
        cor = "brightgreen"
    elif score_final > 60:
        cor = "yellow"
    else:
        cor = "red"

    # --------------------------------------------------------
    # GERA JSON DO BADGE
    # --------------------------------------------------------
    badge = {
        "schemaVersion": 1,
        "label": "score",
        "message": str(score_final),
        "color": cor
    }

    # --------------------------------------------------------
    # SALVA
    # --------------------------------------------------------
    with open(SAIDA, "w") as f:
        json.dump(badge, f)

    print("Badge de score gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_score()