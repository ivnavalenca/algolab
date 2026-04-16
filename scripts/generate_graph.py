# ============================================================
# 📊 GERAÇÃO DE GRÁFICO CIENTÍFICO
# ============================================================
#
# RESPONSABILIDADES:
# - Ler dados do CSV mais recente
# - Plotar desempenho dos algoritmos
# - Exibir barras de erro (desvio padrão)
# - Ajustar curva T(n) = c * n^k
# - Mostrar k (complexidade) e R²
#
# ============================================================

import os
import matplotlib.pyplot as plt

from utils import listar_csvs, carregar_dados, regressao_log_log

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
PASTA = "resultados/historico"
SAIDA = "docs/comparacao.png"

# ============================================================
# 📊 GERAÇÃO DO GRÁFICO
# ============================================================
def gerar_grafico():
    """
    Gera gráfico log-log com:
    - dados reais (mediana)
    - barras de erro (desvio padrão)
    - regressão (linha pontilhada)
    """

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("Nenhum CSV encontrado.")
        return

    # usa o arquivo mais recente
    arquivo = arquivos[-1]

    dados = carregar_dados(os.path.join(PASTA, arquivo))

    plt.figure()

    # ========================================================
    # PARA CADA ALGORITMO
    # ========================================================
    for algoritmo in dados:

        pontos = sorted(dados[algoritmo])

        x = [n for n, _, _ in pontos]
        y = [tempo for _, tempo, _ in pontos]
        erro = [desvio for _, _, desvio in pontos]

        # ====================================================
        # REGRESSÃO
        # ====================================================
        k, c, r2 = regressao_log_log(pontos)

        y_fit = [c * (xi ** k) for xi in x]

        # ====================================================
        # LABEL DO GRÁFICO
        # ====================================================
        label = f"{algoritmo} | k={k:.2f} | R²={r2:.2f}"

        # ====================================================
        # DADOS REAIS COM ERRO
        # ====================================================
        plt.errorbar(
            x,
            y,
            yerr=erro,
            capsize=5,
            marker='o',
            linestyle='-',
            label=label
        )

        # ====================================================
        # CURVA AJUSTADA
        # ====================================================
        plt.plot(x, y_fit, linestyle=":")

    # ========================================================
    # CONFIGURAÇÃO DO GRÁFICO
    # ========================================================
    plt.xscale("log")
    plt.yscale("log")

    plt.xlabel("Tamanho da entrada (log n)")
    plt.ylabel("Tempo de execução (log)")

    plt.title("Análise de Complexidade com Regressão e Erro")

    plt.legend(fontsize=7)
    plt.grid(True)

    # ========================================================
    # SALVAR
    # ========================================================
    os.makedirs("docs", exist_ok=True)

    plt.savefig(SAIDA, dpi=120)

    print("Gráfico gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_grafico()