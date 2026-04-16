# ============================================================
# 📊 BADGE: QUALIDADE (k + R² + NÍVEL)
# ============================================================
#
# RESPONSABILIDADES:
# - Ler métricas dos algoritmos
# - Calcular médias globais (k e R²)
# - Classificar nível do projeto
# - Gerar badge dinâmico (JSON)
#
# ============================================================

import json
import os

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
ARQUIVO_ENTRADA = "docs/resultados/ordenacao_metrics.json"
SAIDA = "docs/resultados/badge_quality.json"

# ============================================================
# 📊 GERAÇÃO DO BADGE
# ============================================================
def gerar_badge():
    """
    Calcula qualidade do projeto com base em:
    - complexidade média (k)
    - qualidade média (R²)
    """

    if not os.path.exists(ARQUIVO_ENTRADA):
        print("Arquivo de métricas não encontrado.")
        return

    # --------------------------------------------------------
    # CARREGA DADOS
    # --------------------------------------------------------
    with open(ARQUIVO_ENTRADA) as f:
        data = json.load(f)

    total_k = 0
    total_r2 = 0
    count = 0

    # --------------------------------------------------------
    # CALCULA MÉDIAS
    # --------------------------------------------------------
    for algoritmo, info in data.items():

        total_k += info.get("k", 0)
        total_r2 += info.get("r2", 0)
        count += 1

    if count == 0:
        print("Sem dados disponíveis.")
        return

    k_medio = total_k / count
    r2_medio = total_r2 / count

    # --------------------------------------------------------
    # CLASSIFICA NÍVEL
    # --------------------------------------------------------
    if r2_medio > 0.95 and k_medio < 1.5:
        nivel = "alto"
        cor = "brightgreen"
    elif r2_medio > 0.90:
        nivel = "medio"
        cor = "yellow"
    else:
        nivel = "baixo"
        cor = "red"

    # --------------------------------------------------------
    # MONTA MENSAGEM
    # --------------------------------------------------------
    mensagem = f"k={k_medio:.2f} | R²={r2_medio:.2f} | {nivel}"

    # --------------------------------------------------------
    # GERA BADGE
    # --------------------------------------------------------
    badge = {
        "schemaVersion": 1,
        "label": "qualidade",
        "message": mensagem,
        "color": cor
    }

    # --------------------------------------------------------
    # SALVA JSON
    # --------------------------------------------------------
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(badge, f)

    print("Badge de qualidade gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_badge()