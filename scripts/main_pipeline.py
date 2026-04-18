# ============================================================
# PIPELINE: main_pipeline
# ============================================================
#
# OBJETIVO:
# Executar toda a pipeline de análise de algoritmos:
#
# ✔ Carregar resultados experimentais (CSV)
# ✔ Processar dados
# ✔ Atualizar histórico (timeline)
# ✔ Gerar gráficos
#
# SAÍDAS:
# ✔ docs/resultados/graficos/*.png
# ✔ docs/assets/readme_chart.png
# ✔ docs/resultados/timeline.csv
#
# ============================================================

import pandas as pd

# ============================================================
# IMPORTS - VISUALIZAÇÃO
# ============================================================

from scripts.visualization import (
    gerar_line_chart,
    gerar_boxplot,
    gerar_comparison_chart,
    gerar_history_chart
)

from scripts.visualization.plot_readme_chart import gerar_readme_chart
from scripts.visualization.plot_complexity_comparison import gerar_complexity_comparison

# ============================================================
# IMPORTS - TIMELINE
# ============================================================

from scripts.generate_timeline import (
    atualizar_timeline,
    preparar_dados_timeline
)

# ============================================================
# CONFIGURAÇÃO
# ============================================================

ARQUIVO_RESULTADOS = "resultados/latest.csv"


# ============================================================
# CARREGAR DADOS
# ============================================================
def carregar_dados():
    """
    Carrega os dados do CSV de resultados.
    """

    print("📥 Carregando dados...")

    df = pd.read_csv(ARQUIVO_RESULTADOS)

    print(f"✔ {len(df)} registros carregados")

    return df


# ============================================================
# PREPARAR DADOS PARA GRÁFICOS DE LINHA
# ============================================================
def preparar_series(df):
    """
    Organiza os dados para gráficos de linha e comparação.
    """

    tamanhos = sorted(df["n"].unique())
    algoritmos = df["algoritmo"].unique()

    series = {}

    for alg in algoritmos:
        dados_alg = df[df["algoritmo"] == alg]
        medias = dados_alg.groupby("n")["tempo"].mean()

        series[alg] = [medias.get(n, 0) for n in tamanhos]

    return tamanhos, series


# ============================================================
# PREPARAR DADOS PARA BOXPLOT
# ============================================================
def preparar_boxplot(df):
    """
    Organiza os dados para boxplot.
    """

    algoritmos = df["algoritmo"].unique()

    data = {}

    for alg in algoritmos:
        data[alg] = df[df["algoritmo"] == alg]["tempo"].tolist()

    return data


# ============================================================
# EXECUÇÃO PRINCIPAL
# ============================================================
def main():
    print("🚀 Iniciando pipeline AlgoLab...\n")

    # --------------------------------------------------------
    # 1. CARREGAR DADOS
    # --------------------------------------------------------
    df = carregar_dados()

    # --------------------------------------------------------
    # 2. PREPARAR DADOS
    # --------------------------------------------------------
    print("🧠 Preparando dados...")

    x, series = preparar_series(df)
    box_data = preparar_boxplot(df)

    # --------------------------------------------------------
    # 3. TIMELINE (HISTÓRICO)
    # --------------------------------------------------------
    print("📈 Atualizando timeline...")

    timeline_df = atualizar_timeline(df)
    x_hist, y_hist = preparar_dados_timeline(timeline_df)

    # --------------------------------------------------------
    # 4. GERAR GRÁFICOS
    # --------------------------------------------------------
    print("📊 Gerando gráficos...\n")

    gerar_line_chart(x, series)
    gerar_comparison_chart(x, series)
    gerar_boxplot(box_data)
    gerar_history_chart(x_hist, y_hist)

    # gráficos extras (diferencial do projeto)
    gerar_readme_chart(x, series)
    gerar_complexity_comparison(x, series)

    print("\n✅ Pipeline finalizada com sucesso!")


# ============================================================
# ENTRYPOINT
# ============================================================
if __name__ == "__main__":
    main()