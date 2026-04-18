# ============================================================
# MÓDULO: history_chart
# ============================================================
#
# OBJETIVO:
# Gerar gráfico de histórico de execução ao longo do tempo.
#
# DESCRIÇÃO:
# Mostra a evolução dos resultados ao longo das execuções
# do pipeline, permitindo identificar tendências.
#
# USO:
# ✔ acompanhar melhorias do projeto
# ✔ detectar regressões de performance
# ✔ análise temporal
#
# ENTRADAS:
# - x: lista de execuções (ex: [1, 2, 3, ...])
# - y: lista de valores (ex: tempo médio)
#
# SAÍDA:
# - Arquivo PNG salvo em:
#   docs/resultados/graficos/history_chart.png
#
# ============================================================

import matplotlib.pyplot as plt
from .plot_utils import *


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================
def gerar_history_chart(x, y):
    """
    Gera gráfico de histórico de execução.

    Parâmetros:
    - x: sequência de execuções
    - y: valores medidos (ex: tempo médio)
    """

    # --------------------------------------------------------
    # PREPARAÇÃO
    # --------------------------------------------------------
    setup_output_dir()
    new_figure()

    # --------------------------------------------------------
    # PLOT
    # --------------------------------------------------------
    plt.plot(x, y, marker='o')

    # --------------------------------------------------------
    # FINALIZAÇÃO
    # --------------------------------------------------------
    finalize_plot(
        titulo="Histórico de Execuções",
        xlabel="Execução",
        ylabel="Tempo (ns)"
    )

    # --------------------------------------------------------
    # EXPORTAÇÃO
    # --------------------------------------------------------
    save_plot("history_chart.png")