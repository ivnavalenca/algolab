# ============================================================
# 📦 UTILITÁRIOS PARA ANÁLISE DE DADOS
# ============================================================
#
# RESPONSABILIDADES:
# - Ler arquivos CSV de resultados
# - Organizar dados por algoritmo
# - Calcular média por algoritmo
# - Executar regressão log-log (k e R²)
#
# ============================================================

import os
import csv
import math

# ============================================================
# 📂 LISTAR ARQUIVOS CSV
# ============================================================
def listar_csvs(pasta):
    """
    Retorna lista ordenada de arquivos CSV em uma pasta.
    """

    return sorted([
        f for f in os.listdir(pasta)
        if f.endswith(".csv")
    ])

# ============================================================
# 📊 CARREGAR DADOS DO CSV
# ============================================================
def carregar_dados(caminho):
    """
    Lê um CSV e organiza os dados por algoritmo.

    Retorno:
    {
        algoritmo: [(n, tempo, desvio), ...]
    }
    """

    dados = {}

    with open(caminho) as f:
        reader = csv.DictReader(f)

        for row in reader:

            algoritmo = row["algoritmo"]
            tamanho = int(row["tamanho"])

            # Usa mediana como principal (ou fallback para tempo)
            tempo = float(row.get("mediana", row.get("tempo", 0)))

            # Desvio padrão (pode não existir em versões antigas)
            desvio = float(row.get("desvio", 0))

            if algoritmo not in dados:
                dados[algoritmo] = []

            dados[algoritmo].append((tamanho, tempo, desvio))

    return dados

# ============================================================
# 📈 MÉDIA POR ALGORITMO
# ============================================================
def media_por_algoritmo(dados):
    """
    Calcula a média dos tempos para cada algoritmo.
    """

    resultado = {}

    for algoritmo in dados:

        tempos = [tempo for _, tempo, _ in dados[algoritmo]]

        resultado[algoritmo] = sum(tempos) / len(tempos)

    return resultado

# ============================================================
# 📉 REGRESSÃO LOG-LOG (COMPLEXIDADE)
# ============================================================
def regressao_log_log(pontos):
    """
    Executa regressão log-log para estimar:

    - k (expoente da complexidade)
    - c (constante)
    - R² (qualidade do ajuste)

    Modelo:
    T(n) = c * n^k
    """

    # transforma em escala log
    xs = [math.log(x) for x, _, _ in pontos]
    ys = [math.log(y) for _, y, _ in pontos]

    n = len(xs)

    # somatórios
    soma_x = sum(xs)
    soma_y = sum(ys)
    soma_xy = sum(x*y for x, y in zip(xs, ys))
    soma_x2 = sum(x*x for x in xs)

    # cálculo de k
    k = (n * soma_xy - soma_x * soma_y) / (n * soma_x2 - soma_x * soma_x)

    # cálculo de c
    log_c = (soma_y - k * soma_x) / n
    c = math.exp(log_c)

    # cálculo do R²
    media_y = sum(ys) / n

    ss_total = sum((y - media_y) ** 2 for y in ys)
    ss_residual = sum((ys[i] - (log_c + k * xs[i])) ** 2 for i in range(n))

    r2 = 1 - (ss_residual / ss_total)

    return k, c, r2