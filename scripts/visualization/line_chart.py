# ============================================================
# MÓDULO: line_chart
# ============================================================
#
# OBJETIVO:
# Gerar gráfico de linhas para comparação de algoritmos.
#
# DESCRIÇÃO:
# Cada algoritmo é representado como uma linha no gráfico,
# permitindo comparar desempenho ao longo do tamanho de entrada.
#
# ENTRADAS:
# - x: lista de tamanhos de entrada
# - series: dicionário no formato:
#     {
#         "Algoritmo A": [valores],
#         "Algoritmo B": [valores]
#     }
#
# SAÍDA:
# - Arquivo PNG salvo em:
#   docs/resultados/graficos/line_chart.png
#
# ============================================================

import matplotlib.pyplot as plt
from .plot_utils import *


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================
def gerar_line_chart(x, series):
    """
    Gera gráfico de linhas comparando algoritmos.

    Parâmetros:
    - x: lista de valores do eixo X (ex: tamanhos de entrada)
    - series: dicionário com nome do algoritmo e seus tempos
    """

    # --------------------------------------------------------
    # PREPARAÇÃO
    # --------------------------------------------------------
    setup_output_dir()
    new_figure()

    # --------------------------------------------------------
    # PLOT DAS SÉRIES
    # --------------------------------------------------------
    for nome_algoritmo, valores in series.items():
        plt.plot(x, valores, marker='o', label=nome_algoritmo)

    # --------------------------------------------------------
    # FINALIZAÇÃO
    # --------------------------------------------------------
    finalize_plot(
        titulo="Comparação de Algoritmos",
        xlabel="Tamanho da Entrada",
        ylabel="Tempo de Execução (ns)",
        legenda=True
    )

    # --------------------------------------------------------
    # EXPORTAÇÃO
    # --------------------------------------------------------
    save_plot("line_chart.png")