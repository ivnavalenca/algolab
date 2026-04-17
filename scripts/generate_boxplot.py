# ============================================================
# 📊 BOXPLOT MULTI-CENÁRIO (PADRÃO ALGOLAB)
# ============================================================
#
# RESPONSABILIDADES:
# - Ler histórico de execuções (CSV)
# - Agrupar tempos por algoritmo
# - Gerar boxplot por cenário
# - Exibir distribuição + outliers
# - Exportar gráficos para o dashboard
#
# SAÍDA:
# docs/resultados/graficos/boxplot_<cenario>.png
#
# ============================================================

import os
import matplotlib.pyplot as plt

from utils import listar_csvs, carregar_dados

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
PASTA = "resultados/historico"
PASTA_SAIDA = "docs/resultados/graficos"

# ============================================================
# 📊 GERAÇÃO DOS BOXPLOTS
# ============================================================
def gerar_boxplots():
    """
    Gera boxplots para cada cenário encontrado no histórico.
    """

    # --------------------------------------------------------
    # GARANTE DIRETÓRIO DE SAÍDA
    # --------------------------------------------------------
    os.makedirs(PASTA_SAIDA, exist_ok=True)

    # --------------------------------------------------------
    # LISTA CSVs
    # --------------------------------------------------------
    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("Nenhum CSV encontrado.")
        return

    # ========================================================
    # LOOP POR ARQUIVO (CENÁRIO)
    # ========================================================
    for arquivo in arquivos:

        caminho = os.path.join(PASTA, arquivo)

        # ----------------------------------------------------
        # CARREGA DADOS
        # ----------------------------------------------------
        dados = carregar_dados(caminho)

        labels = []
        valores = []

        # ----------------------------------------------------
        # ORGANIZA POR ALGORITMO
        # ----------------------------------------------------
        for algoritmo in dados:

            pontos = dados[algoritmo]

            # estrutura esperada: (tamanho, tempo, cenario)
            tempos = [tempo for _, tempo, _ in pontos]

            if not tempos:
                continue

            labels.append(algoritmo)
            valores.append(tempos)

        if not valores:
            print(f"Sem dados válidos em {arquivo}")
            continue

        # ----------------------------------------------------
        # IDENTIFICA CENÁRIO
        # ----------------------------------------------------
        try:
            cenario = list(dados.values())[0][0][2]
        except Exception:
            cenario = arquivo.replace(".csv", "")

        # ----------------------------------------------------
        # CRIA BOXPLOT
        # ----------------------------------------------------
        plt.figure(figsize=(8, 5))

        plt.boxplot(
            valores,
            labels=labels,
            showfliers=True  # exibe outliers
        )

        plt.title(f"Distribuição dos Tempos - {cenario}")
        plt.xlabel("Algoritmo")
        plt.ylabel("Tempo")

        plt.xticks(rotation=30)
        plt.grid(True)

        # ----------------------------------------------------
        # SALVA ARQUIVO
        # ----------------------------------------------------
        caminho_saida = f"{PASTA_SAIDA}/boxplot_{cenario}.png"

        plt.savefig(caminho_saida, dpi=120)
        plt.close()

        print(f"Boxplot gerado: {caminho_saida}")

# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_boxplots()