# ============================================================
# MÓDULO: comparison_chart
# ============================================================
#
# OBJETIVO:
# Gerar gráfico comparativo detalhado entre algoritmos.
#
# DESCRIÇÃO:
# Similar ao line_chart, mas com foco em comparação direta
# entre algoritmos em um mesmo cenário.
#
# DIFERENCIAL:
# ✔ foco em análise comparativa
# ✔ visual mais direto
# ✔ ideal para dashboard
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
#   docs/resultados/graficos/comparison_chart.png
#
# ============================================================

import matplotlib.pyplot as plt
from .plot_utils import *


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================
def gerar_comparison_chart(x, series):
    """
    Gera gráfico comparativo entre algoritmos.

    Parâmetros:
    - x: valores do eixo X (ex: tamanhos)
    - series: dicionário com tempos por algoritmo
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
        plt.plot(x, valores, linewidth=2, label=nome_algoritmo)

    # --------------------------------------------------------
    # FINALIZAÇÃO
    # --------------------------------------------------------
    finalize_plot(
        titulo="Comparação Detalhada de Algoritmos",
        xlabel="Tamanho da Entrada",
        ylabel="Tempo de Execução (ns)",
        legenda=True
    )

    # --------------------------------------------------------
    # EXPORTAÇÃO
    # --------------------------------------------------------
    save_plot("comparison_chart.png")