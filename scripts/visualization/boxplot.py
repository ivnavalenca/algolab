# ============================================================
# MÓDULO: boxplot
# ============================================================
#
# OBJETIVO:
# Gerar gráfico boxplot para análise estatística dos tempos
# de execução dos algoritmos.
#
# DESCRIÇÃO:
# O boxplot permite visualizar:
# ✔ mediana
# ✔ quartis
# ✔ dispersão
# ✔ outliers
#
# ENTRADAS:
# - data_dict: dicionário no formato:
#     {
#         "Algoritmo A": [valores],
#         "Algoritmo B": [valores]
#     }
#
# SAÍDA:
# - Arquivo PNG salvo em:
#   docs/resultados/graficos/boxplot_geral.png
#
# ============================================================

import matplotlib.pyplot as plt
from .plot_utils import *


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================
def gerar_boxplot(data_dict):
    """
    Gera um boxplot comparando a distribuição dos algoritmos.

    Parâmetros:
    - data_dict: dicionário com listas de tempos por algoritmo
    """

    # --------------------------------------------------------
    # PREPARAÇÃO
    # --------------------------------------------------------
    setup_output_dir()
    new_figure()

    # --------------------------------------------------------
    # ORGANIZAÇÃO DOS DADOS
    # --------------------------------------------------------
    dados = list(data_dict.values())
    labels = list(data_dict.keys())

    # --------------------------------------------------------
    # PLOT DO BOXPLOT
    # --------------------------------------------------------
    plt.boxplot(dados, labels=labels, patch_artist=True)

    # --------------------------------------------------------
    # FINALIZAÇÃO
    # --------------------------------------------------------
    finalize_plot(
        titulo="Distribuição dos Tempos de Execução",
        ylabel="Tempo (ns)"
    )

    # --------------------------------------------------------
    # EXPORTAÇÃO
    # --------------------------------------------------------
    save_plot("boxplot_geral.png")