# ============================================================
# MÓDULO: plot_utils
# ============================================================
#
# OBJETIVO:
# Centralizar toda a configuração e utilidades de geração
# de gráficos do projeto AlgoLab.
#
# RESPONSABILIDADES:
# ✔ Definir diretório padrão de saída (docs/)
# ✔ Padronizar estilo visual dos gráficos
# ✔ Criar figuras consistentes
# ✔ Aplicar títulos, labels e legendas
# ✔ Salvar gráficos corretamente
#
# BENEFÍCIOS:
# ✔ Evita código duplicado
# ✔ Mantém padrão visual único
# ✔ Facilita manutenção
#
# ============================================================

import os
import matplotlib.pyplot as plt

# ============================================================
# CONFIGURAÇÃO GLOBAL
# ============================================================

# Diretório onde os gráficos serão salvos
# IMPORTANTE: já integrado com GitHub Pages
BASE_DIR = "docs/resultados/graficos"


# ============================================================
# CRIA DIRETÓRIO DE SAÍDA
# ============================================================
def setup_output_dir():
    """
    Garante que o diretório de saída existe.

    Se não existir, ele é criado automaticamente.
    """
    os.makedirs(BASE_DIR, exist_ok=True)


# ============================================================
# CONFIGURA ESTILO GLOBAL DOS GRÁFICOS
# ============================================================
def setup_style():
    """
    Define o estilo padrão dos gráficos.

    Mantém consistência visual em todo o projeto.
    """

    plt.style.use("default")

    plt.rcParams.update({
        "figure.figsize": (8, 5),
        "axes.grid": True,
        "font.size": 10,
        "axes.titlesize": 12,
        "axes.labelsize": 10
    })


# ============================================================
# CRIA UMA NOVA FIGURA
# ============================================================
def new_figure():
    """
    Inicializa uma nova figura com o padrão definido.
    """

    setup_style()
    plt.figure()


# ============================================================
# FINALIZA O GRÁFICO
# ============================================================
def finalize_plot(titulo=None, xlabel=None, ylabel=None, legenda=False):
    """
    Aplica elementos finais ao gráfico.

    Parâmetros:
    - titulo: título do gráfico
    - xlabel: label do eixo X
    - ylabel: label do eixo Y
    - legenda: se True, exibe legenda
    """

    if titulo:
        plt.title(titulo)

    if xlabel:
        plt.xlabel(xlabel)

    if ylabel:
        plt.ylabel(ylabel)

    if legenda:
        plt.legend()


# ============================================================
# SALVA O GRÁFICO
# ============================================================
def save_plot(nome_arquivo):
    """
    Salva o gráfico no diretório padrão.

    Parâmetros:
    - nome_arquivo: nome do arquivo (ex: "line_chart.png")
    """

    caminho = f"{BASE_DIR}/{nome_arquivo}"

    plt.tight_layout()
    plt.savefig(caminho)
    plt.close()

    print(f"📊 gráfico salvo em: {caminho}")