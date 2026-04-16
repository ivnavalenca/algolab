# ============================================================
# 🧠 BADGE: MATURIDADE DO PROJETO
# ============================================================
#
# RESPONSABILIDADES:
# - Avaliar nível de engenharia do projeto
# - Verificar presença de funcionalidades-chave
# - Classificar maturidade (básico → enterprise)
# - Gerar badge dinâmico (JSON)
#
# ============================================================

import os
import json

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
SAIDA = "docs/resultados/badge_maturity.json"

# ============================================================
# 🧠 AVALIAÇÃO DE MATURIDADE
# ============================================================
def gerar_maturidade():
    """
    Avalia o nível do projeto com base em critérios de engenharia.
    """

    pontos = 0

    # --------------------------------------------------------
    # CRITÉRIOS DE MATURIDADE
    # --------------------------------------------------------

    # ✔ CI/CD
    if os.path.exists(".github/workflows/benchmark.yml"):
        pontos += 1

    # ✔ análise de dados
    if os.path.exists("scripts/generate_graph.py"):
        pontos += 1

    # ✔ relatório automatizado
    if os.path.exists("scripts/generate_report.py"):
        pontos += 1

    # ✔ dashboard web
    if os.path.exists("docs/index.html"):
        pontos += 1

    # ✔ ranking inteligente
    if os.path.exists("scripts/generate_ranking.py"):
        pontos += 1

    # --------------------------------------------------------
    # CLASSIFICAÇÃO FINAL
    # --------------------------------------------------------
    if pontos >= 5:
        nivel = "enterprise"
        cor = "blue"
    elif pontos >= 3:
        nivel = "avancado"
        cor = "brightgreen"
    elif pontos >= 2:
        nivel = "intermediario"
        cor = "yellow"
    else:
        nivel = "basico"
        cor = "red"

    # --------------------------------------------------------
    # GERA BADGE
    # --------------------------------------------------------
    badge = {
        "schemaVersion": 1,
        "label": "maturidade",
        "message": nivel,
        "color": cor
    }

    # --------------------------------------------------------
    # SALVA JSON
    # --------------------------------------------------------
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(badge, f)

    print("Badge de maturidade gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_maturidade()