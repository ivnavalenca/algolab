# ============================================================
# MÓDULO: plot_complexity_comparison
# ============================================================
#
# OBJETIVO:
# Comparar desempenho real dos algoritmos com suas
# complexidades teóricas.
#
# DESCRIÇÃO:
# Este gráfico permite visualizar se os resultados empíricos
# seguem o comportamento esperado das funções:
#
# ✔ O(n)
# ✔ O(n log n)
# ✔ O(n²)
#
# USO:
# ✔ análise acadêmica
# ✔ validação experimental
# ✔ apoio em apresentação / banca
#
# ENTRADAS:
# - x: lista de tamanhos de entrada
# - series_real: dicionário com dados reais:
#     {
#         "Algoritmo A": [valores],
#         "Algoritmo B": [valores]
#     }
#
# SAÍDA:
# - docs/resultados/graficos/complexity_comparison.png
#
# ============================================================

import numpy as np
import matplotlib.pyplot as plt

from .plot_utils import *


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================
def gerar_complexity_comparison(x, series_real):
    """
    Gera gráfico comparando dados reais com curvas teóricas.

    Parâmetros:
    - x: lista de tamanhos
    - series_real: dados reais dos algoritmos
    """

    # --------------------------------------------------------
    # PREPARAÇÃO
    # --------------------------------------------------------
    setup_output_dir()
    new_figure()

    x_np = np.array(x)

    # --------------------------------------------------------
    # CURVAS TEÓRICAS NORMALIZADAS
    # --------------------------------------------------------
    n = x_np
    n_log_n = x_np * np.log2(x_np + 1)
    n2 = x_np ** 2

    # normalização para escala visual comparável
    def normalizar(y):
        return y / max(y)

    plt.plot(x, normalizar(n), linestyle="--", label="O(n)")
    plt.plot(x, normalizar(n_log_n), linestyle="--", label="O(n log n)")
    plt.plot(x, normalizar(n2), linestyle="--", label="O(n²)")

    # --------------------------------------------------------
    # DADOS REAIS (NORMALIZADOS)
    # --------------------------------------------------------
    for nome, valores in series_real.items():
        valores_np = np.array(valores)
        plt.plot(x, normalizar(valores_np), linewidth=2, label=nome)

    # --------------------------------------------------------
    # FINALIZAÇÃO
    # --------------------------------------------------------
    finalize_plot(
        titulo="Comparação: Teórico vs Empírico",
        xlabel="Tamanho da Entrada",
        ylabel="Escala Normalizada",
        legenda=True
    )

    # --------------------------------------------------------
    # EXPORTAÇÃO
    # --------------------------------------------------------
    save_plot("complexity_comparison.png")