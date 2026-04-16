# ============================================================
# 📊 GERAÇÃO DE BOXPLOT (DISTRIBUIÇÃO DOS DADOS)
# ============================================================
#
# RESPONSABILIDADES:
# - Ler dados do CSV mais recente
# - Agrupar tempos por algoritmo
# - Gerar boxplot com distribuição
# - Exibir outliers reais
#
# ============================================================

import os
import matplotlib.pyplot as plt

from utils import listar_csvs, carregar_dados

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
PASTA = "resultados/historico"
SAIDA = "docs/boxplot.png"

# ============================================================
# 📊 GERAÇÃO DO BOXPLOT
# ============================================================
def gerar_boxplot():
    """
    Gera um boxplot para visualizar a distribuição dos tempos
    de execução de cada algoritmo.
    """

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("Nenhum CSV encontrado.")
        return

    # usa o arquivo mais recente
    arquivo = arquivos[-1]

    dados = carregar_dados(os.path.join(PASTA, arquivo))

    labels = []
    valores = []

    # ========================================================
    # ORGANIZA DADOS
    # ========================================================
    for algoritmo in dados:

        pontos = dados[algoritmo]

        tempos = [tempo for _, tempo, _ in pontos]

        labels.append(algoritmo)
        valores.append(tempos)

    # ========================================================
    # CRIA GRÁFICO
    # ========================================================
    plt.figure()

    plt.boxplot(
        valores,
        labels=labels,
        showfliers=True  # mostra outliers
    )

    plt.title("Distribuição dos Tempos de Execução")
    plt.ylabel("Tempo (mediana)")

    plt.xticks(rotation=30)

    plt.grid(True)

    # ========================================================
    # SALVAR
    # ========================================================
    os.makedirs("docs", exist_ok=True)

    plt.savefig(SAIDA, dpi=120)

    print("Boxplot gerado em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_boxplot()