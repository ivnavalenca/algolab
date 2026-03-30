import csv
import matplotlib.pyplot as plt

"""
============================================================
SCRIPT: generate_readme_chart.py
============================================================

OBJETIVO:
Gerar gráfico de performance da execução atual para o README.

ENTRADA:
resultados/latest.csv

SAÍDA:
docs/performance.png

============================================================
"""

INPUT_FILE = "resultados/latest.csv"
OUTPUT_FILE = "docs/performance.png"


def carregar_csv(path):
    dados = {}

    with open(path) as f:
        reader = csv.DictReader(f)

        for row in reader:
            algoritmo = row["algoritmo"]
            tamanho = int(row["tamanho"])
            tempo = float(row["tempo"])

            if algoritmo not in dados:
                dados[algoritmo] = {"x": [], "y": []}

            dados[algoritmo]["x"].append(tamanho)
            dados[algoritmo]["y"].append(tempo)

    return dados


def gerar_grafico(dados):
    plt.figure(figsize=(10, 6))

    for algoritmo, valores in dados.items():
        plt.plot(
            valores["x"],
            valores["y"],
            marker='o',
            label=algoritmo
        )

    plt.xlabel("Tamanho da Entrada (n)")
    plt.ylabel("Tempo (ns)")
    plt.title("📊 Comparação de Algoritmos (Execução Atual)")

    plt.legend()
    plt.grid(True)

    plt.tight_layout()
    plt.savefig(OUTPUT_FILE)

    print(f"📊 Gráfico salvo em {OUTPUT_FILE}")


def main():
    dados = carregar_csv(INPUT_FILE)

    if not dados:
        print("⚠️ Nenhum dado encontrado.")
        return

    gerar_grafico(dados)


if __name__ == "__main__":
    main()