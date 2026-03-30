import json
import matplotlib.pyplot as plt

"""
============================================================
SCRIPT: generate_history_chart.py
============================================================

OBJETIVO:
Gerar gráfico temporal de performance a partir do histórico.

ENTRADA:
docs/performance_history.json

SAÍDA:
docs/performance.png

============================================================
"""

HISTORY_FILE = "docs/performance_history.json"
OUTPUT_FILE = "docs/performance.png"


def carregar_historico():
    with open(HISTORY_FILE) as f:
        return json.load(f)


def preparar_dados(historico):
    dados_por_algoritmo = {}

    for entry in historico:
        for alg, valor in entry["dados"].items():
            if alg not in dados_por_algoritmo:
                dados_por_algoritmo[alg] = []

            dados_por_algoritmo[alg].append(valor)

    return dados_por_algoritmo


def gerar_grafico(dados_por_algoritmo):
    plt.figure(figsize=(10, 6))

    for alg, valores in dados_por_algoritmo.items():
        plt.plot(valores, marker='o', label=alg)

    plt.xlabel("Execuções")
    plt.ylabel("Tempo médio (ns)")
    plt.title("📈 Evolução da Performance dos Algoritmos")

    plt.legend()
    plt.grid(True)

    plt.tight_layout()
    plt.savefig(OUTPUT_FILE)

    print(f"📊 Gráfico salvo em {OUTPUT_FILE}")


def main():
    historico = carregar_historico()

    if not historico:
        print("⚠️ Histórico vazio.")
        return

    dados = preparar_dados(historico)
    gerar_grafico(dados)


if __name__ == "__main__":
    main()