import json
import csv
import sys

"""
============================================================
SCRIPT: badge.py
============================================================

OBJETIVO:
Gerar badge de performance (formato shields.io endpoint)

ENTRADA:
python badge.py base.csv atual.csv

SAÍDA:
docs/badge.json

============================================================
"""

THRESHOLD = 1.2  # regressão > 20%


def carregar_csv(path):
    dados = {}

    with open(path) as f:
        reader = csv.DictReader(f)

        for row in reader:
            algoritmo = row["algoritmo"]
            tempo = float(row["tempo"])

            if algoritmo not in dados:
                dados[algoritmo] = []

            dados[algoritmo].append(tempo)

    return {
        alg: sum(valores) / len(valores)
        for alg, valores in dados.items()
    }


def avaliar(base, atual):

    # prioridade: regressão > melhoria > estável
    houve_regressao = False
    houve_melhoria = False

    for alg in base:

        if alg not in atual:
            continue

        antes = base[alg]
        depois = atual[alg]

        fator = depois / antes if antes > 0 else 1

        if fator > THRESHOLD:
            houve_regressao = True

        elif fator < 0.9:
            houve_melhoria = True

    if houve_regressao:
        return "regression", "red"

    if houve_melhoria:
        return "improving", "brightgreen"

    return "stable", "yellow"


def gerar_badge(status, color):

    badge = {
        "schemaVersion": 1,
        "label": "Performance",
        "message": status,
        "color": color
    }

    return badge


def salvar_badge(badge):

    with open("docs/badge.json", "w") as f:
        json.dump(badge, f)

    print("🏷️ Badge atualizado em docs/badge.json")


def main():

    if len(sys.argv) < 3:
        print("Uso: python badge.py base.csv atual.csv")
        sys.exit(1)

    base = carregar_csv(sys.argv[1])
    atual = carregar_csv(sys.argv[2])

    status, color = avaliar(base, atual)

    badge = gerar_badge(status, color)
    salvar_badge(badge)


if __name__ == "__main__":
    main()