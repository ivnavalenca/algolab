# ============================================================
# 📈 TIMELINE HISTÓRICA DE DESEMPENHO
# ============================================================
#
# RESPONSABILIDADES:
# - Ler todos os CSVs históricos
# - Extrair data de cada execução
# - Agrupar dados por algoritmo
# - Calcular tempo médio por execução
# - Gerar JSON para visualização temporal
#
# ============================================================

import os
import json
import csv

# ============================================================
# 📁 CONFIGURAÇÕES
# ============================================================
PASTA = "resultados/historico"
SAIDA = "docs/resultados/ordenacao_timeline.json"

# ============================================================
# 📂 LISTAR CSVs
# ============================================================
def listar_csvs():
    """
    Retorna lista ordenada de arquivos CSV.
    """
    return sorted([
        f for f in os.listdir(PASTA)
        if f.endswith(".csv")
    ])

# ============================================================
# 📅 EXTRAIR DATA DO NOME DO ARQUIVO
# ============================================================
def extrair_data(nome):
    """
    Extrai data do nome do arquivo.

    Exemplo:
    lista03_2026-04-16.csv → 2026-04-16
    """
    return nome.replace(".csv", "").split("_")[-1]

# ============================================================
# 📊 CARREGAR HISTÓRICO
# ============================================================
def carregar_timeline():
    """
    Constrói timeline por algoritmo.

    Retorno:
    {
        algoritmo: [
            { data, tempo },
            ...
        ]
    }
    """

    arquivos = listar_csvs()

    timeline = {}

    for arquivo in arquivos:

        data = extrair_data(arquivo)

        caminho = os.path.join(PASTA, arquivo)

        with open(caminho) as f:

            reader = csv.DictReader(f)

            # agrupa tempos por algoritmo nessa execução
            temp_execucao = {}

            for row in reader:

                algoritmo = row["algoritmo"]

                # usa mediana (mais robusto)
                tempo = float(row.get("mediana", row.get("tempo", 0)))

                if algoritmo not in temp_execucao:
                    temp_execucao[algoritmo] = []

                temp_execucao[algoritmo].append(tempo)

            # calcula média por algoritmo na execução
            for algoritmo in temp_execucao:

                media = sum(temp_execucao[algoritmo]) / len(temp_execucao[algoritmo])

                if algoritmo not in timeline:
                    timeline[algoritmo] = []

                timeline[algoritmo].append({
                    "data": data,
                    "tempo": media
                })

    return timeline

# ============================================================
# 📊 GERAR JSON
# ============================================================
def gerar_timeline():
    """
    Gera arquivo JSON com timeline histórica.
    """

    dados = carregar_timeline()

    os.makedirs("docs/resultados", exist_ok=True)

    with open(SAIDA, "w") as f:
        json.dump(dados, f, indent=2)

    print("Timeline gerada em:", SAIDA)


# ============================================================
# 🚀 EXECUÇÃO
# ============================================================
if __name__ == "__main__":
    gerar_timeline()