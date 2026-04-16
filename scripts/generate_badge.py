# ============================================================
# 🚨 BADGE: DETECÇÃO DE REGRESSÃO
# ============================================================
#
# RESPONSABILIDADES:
# - Ler ranking dos algoritmos
# - Identificar presença de regressão
# - Detectar instabilidade
# - Gerar badge dinâmico (JSON)
#
# ============================================================

import json
import os

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
ARQUIVO_ENTRADA = "docs/resultados/ordenacao_ranking.json"
SAIDA = "docs/resultados/badge_regressao.json"

# ============================================================
# 🚨 ANÁLISE DE STATUS
# ============================================================
def gerar_badge():
    """
    Analisa o ranking e detecta:
    - REGRESSAO
    - INSTAVEL
    - OK
    """

    if not os.path.exists(ARQUIVO_ENTRADA):
        print("Arquivo de ranking não encontrado.")
        return

    # --------------------------------------------------------
    # CARREGA DADOS
    # --------------------------------------------------------
    with open(ARQUIVO_ENTRADA) as f:
        data = json.load(f)

    status_final = "OK"

    # --------------------------------------------------------
    # VERIFICA ALGORITMOS
    # --------------------------------------------------------
    for algoritmo, info in data.items():

        status = info.get("status", "OK")

        if status == "REGRESSAO":
            status_final = "REGRESSAO"
            break

        if status == "INSTAVEL":
            status_final = "INSTAVEL"

    # --------------------------------------------------------
    # DEFINE COR
    # --------------------------------------------------------
    if status_final == "OK":
        cor = "brightgreen"
    elif status_final == "INSTAVEL":
        cor = "yellow"
    else:
        cor = "red"

    # --------------------------------------------------------
    # GERA BADGE
    # --------------------------------------------------------
    badge = {
        "schemaVersion": 1,
        "label": "regressao",
        "message": status_final,
        "color": cor
    }

    # --------------------------------------------------------
    # SALVA JSON
    # --------------------------------------------------------
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(badge, f)

    print("Badge de regressão gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_badge()