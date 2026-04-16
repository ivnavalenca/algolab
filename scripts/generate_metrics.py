# ============================================================
# 📊 GERAÇÃO DE MÉTRICAS (k e R²)
# ============================================================
#
# RESPONSABILIDADES:
# - Ler o CSV mais recente
# - Calcular regressão log-log
# - Extrair k (complexidade)
# - Extrair R² (qualidade)
# - Gerar arquivo JSON para dashboard
#
# ============================================================

import os
import json

from utils import listar_csvs, carregar_dados, regressao_log_log

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
PASTA = "resultados/historico"
SAIDA = "docs/resultados/ordenacao_metrics.json"

# ============================================================
# 📊 GERAÇÃO DE MÉTRICAS
# ============================================================
def gerar_metricas():
    """
    Gera métricas de complexidade para cada algoritmo:
    - k (expoente)
    - R² (qualidade do ajuste)
    """

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("Nenhum CSV encontrado.")
        return

    # usa o CSV mais recente
    arquivo = arquivos[-1]

    dados = carregar_dados(os.path.join(PASTA, arquivo))

    resultado = {}

    # ========================================================
    # PARA CADA ALGORITMO
    # ========================================================
    for algoritmo in dados:

        pontos = dados[algoritmo]

        # regressão
        k, _, r2 = regressao_log_log(pontos)

        resultado[algoritmo] = {
            "k": round(k, 3),
            "r2": round(r2, 3)
        }

    # ========================================================
    # SALVAR JSON
    # ========================================================
    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(resultado, f, indent=2)

    print("Métricas geradas em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_metricas()