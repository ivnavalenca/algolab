# ============================================================
# MÓDULO: plot_interactive
# ============================================================
#
# OBJETIVO:
# Gerar gráfico interativo utilizando Plotly.
#
# DESCRIÇÃO:
# Permite:
# ✔ zoom
# ✔ hover (detalhes)
# ✔ ativar/desativar algoritmos
#
# USO:
# ✔ análise exploratória
# ✔ apresentação interativa
#
# SAÍDA:
# - docs/resultados/graficos/interactive_chart.html
#
# OBS:
# Este arquivo NÃO é exibido diretamente no GitHub Pages.
#
# ============================================================

import os
import plotly.graph_objects as go

# ============================================================
# DIRETÓRIO DE SAÍDA
# ============================================================

BASE_DIR = "docs/resultados/graficos"


# ============================================================
# FUNÇÃO PRINCIPAL
# ============================================================
def gerar_interactive_chart(x, series):
    """
    Gera gráfico interativo com Plotly.

    Parâmetros:
    - x: lista de tamanhos
    - series: dicionário com dados dos algoritmos
    """

    # --------------------------------------------------------
    # GARANTIR DIRETÓRIO
    # --------------------------------------------------------
    os.makedirs(BASE_DIR, exist_ok=True)

    # --------------------------------------------------------
    # CRIAR FIGURA
    # --------------------------------------------------------
    fig = go.Figure()

    # --------------------------------------------------------
    # ADICIONAR SÉRIES
    # --------------------------------------------------------
    for nome, valores in series.items():
        fig.add_trace(go.Scatter(
            x=x,
            y=valores,
            mode='lines+markers',
            name=nome
        ))

    # --------------------------------------------------------
    # LAYOUT
    # --------------------------------------------------------
    fig.update_layout(
        title="Comparação Interativa de Algoritmos",
        xaxis_title="Tamanho da Entrada",
        yaxis_title="Tempo",
        template="plotly_white"
    )

    # --------------------------------------------------------
    # EXPORTAÇÃO
    # --------------------------------------------------------
    caminho = f"{BASE_DIR}/interactive_chart.html"

    fig.write_html(caminho)

    print(f"📊 gráfico interativo salvo em: {caminho}")