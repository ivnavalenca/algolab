# ============================================================
# 🏆 RANKING INTELIGENTE DE ALGORITMOS
# ============================================================
#
# RESPONSABILIDADES:
# - Ler dados do CSV mais recente
# - Avaliar desempenho (tempo)
# - Avaliar estabilidade (desvio)
# - Avaliar complexidade (k)
# - Avaliar qualidade (R²)
# - Gerar score final
# - Detectar alertas (regressão, instabilidade)
#
# ============================================================

import os
import json

from utils import listar_csvs, carregar_dados, regressao_log_log

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
PASTA = "resultados/historico"
SAIDA = "docs/resultados/ordenacao_ranking.json"

# ============================================================
# 📊 CÁLCULO DE SCORE
# ============================================================
def calcular_score(pontos):
    """
    Calcula score baseado em:
    - tempo médio
    - desvio médio
    - complexidade (k)
    - qualidade (R²)
    """

    tempos = [t for _, t, _ in pontos]
    desvios = [d for _, _, d in pontos]

    media_tempo = sum(tempos) / len(tempos)
    media_desvio = sum(desvios) / len(desvios)

    k, _, r2 = regressao_log_log(pontos)

    # ========================================================
    # NORMALIZAÇÃO (quanto menor, melhor)
    # ========================================================
    tempo_score = 1 / (1 + media_tempo)
    desvio_score = 1 / (1 + media_desvio)

    # ========================================================
    # QUALIDADE (quanto maior, melhor)
    # ========================================================
    r2_score = r2

    # penaliza complexidade alta
    complexidade_score = 1 / (1 + k)

    # ========================================================
    # SCORE FINAL (ponderado)
    # ========================================================
    score = (
        0.4 * tempo_score +
        0.2 * desvio_score +
        0.2 * r2_score +
        0.2 * complexidade_score
    )

    return score, k, r2

# ============================================================
# 🚨 DETECÇÃO DE ALERTAS
# ============================================================
def detectar_status(score, k, r2):
    """
    Classifica o algoritmo com base no desempenho.
    """

    if r2 < 0.85:
        return "INSTAVEL"

    if k > 2:
        return "COMPLEXO"

    if score < 0.3:
        return "REGRESSAO"

    return "OK"

# ============================================================
# 📊 GERAÇÃO DO RANKING
# ============================================================
def gerar_ranking():
    """
    Gera ranking dos algoritmos com score e status.
    """

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("Nenhum CSV encontrado.")
        return

    arquivo = arquivos[-1]

    dados = carregar_dados(os.path.join(PASTA, arquivo))

    ranking = {}

    # ========================================================
    # PARA CADA ALGORITMO
    # ========================================================
    for algoritmo in dados:

        pontos = dados[algoritmo]

        score, k, r2 = calcular_score(pontos)
        status = detectar_status(score, k, r2)

        ranking[algoritmo] = {
            "score": round(score, 3),
            "k": round(k, 2),
            "r2": round(r2, 2),
            "status": status
        }

    # ========================================================
    # SALVAR JSON
    # ========================================================
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(ranking, f, indent=2)

    print("Ranking gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_ranking()