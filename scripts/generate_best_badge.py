# ============================================================
# 🏆 BADGE: MELHOR ALGORITMO
# ============================================================
#
# RESPONSABILIDADES:
# - Ler ranking dos algoritmos
# - Identificar o melhor (maior score)
# - Classificar qualidade do resultado
# - Gerar badge dinâmico (JSON)
#
# ============================================================

import json
import os

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
ARQUIVO_ENTRADA = "docs/resultados/ordenacao_ranking.json"
SAIDA = "docs/resultados/badge_best.json"

# ============================================================
# 🏆 IDENTIFICA MELHOR ALGORITMO
# ============================================================
def gerar_badge():
    """
    Seleciona o algoritmo com maior score.
    """

    if not os.path.exists(ARQUIVO_ENTRADA):
        print("Arquivo de ranking não encontrado.")
        return

    # --------------------------------------------------------
    # CARREGA DADOS
    # --------------------------------------------------------
    with open(ARQUIVO_ENTRADA) as f:
        data = json.load(f)

    melhor_algoritmo = None
    melhor_score = -1

    # --------------------------------------------------------
    # BUSCA MAIOR SCORE
    # --------------------------------------------------------
    for nome, info in data.items():

        score = info.get("score", 0)

        if score > melhor_score:
            melhor_score = score
            melhor_algoritmo = nome

    if melhor_algoritmo is None:
        print("Nenhum algoritmo encontrado.")
        return

    # --------------------------------------------------------
    # DEFINE COR DO BADGE
    # --------------------------------------------------------
    if melhor_score > 0.8:
        cor = "brightgreen"
    elif melhor_score > 0.5:
        cor = "yellow"
    else:
        cor = "red"

    # --------------------------------------------------------
    # GERA BADGE
    # --------------------------------------------------------
    badge = {
        "schemaVersion": 1,
        "label": "best",
        "message": melhor_algoritmo,
        "color": cor
    }

    # --------------------------------------------------------
    # SALVA JSON
    # --------------------------------------------------------
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(badge, f)

    print("Badge de melhor algoritmo gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_badge()