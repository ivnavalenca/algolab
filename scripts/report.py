import csv
import sys

"""
============================================================
SCRIPT: report.py
============================================================

OBJETIVO:
Gerar relatório de performance em Markdown para PR.

ENTRADA:
python report.py base.csv atual.csv

SAÍDA:
stdout (Markdown) → usado no CI para comentar no PR

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


def gerar_relatorio(base, atual):

    linhas = []
    linhas.append("## 📊 Relatório de Performance\n")

    melhorias = 0
    regressos = 0

    for algoritmo in atual:

        if algoritmo not in base:
            continue

        antes = base[algoritmo]
        depois = atual[algoritmo]

        fator = depois / antes if antes > 0 else 1

        if fator > THRESHOLD:
            status = "❌ Regressão"
            regressos += 1

        elif fator < 0.9:
            status = "🚀 Melhoria"
            melhorias += 1

        else:
            status = "➖ Estável"

        linhas.append(
            f"- **{algoritmo}**: {antes:.2f} → {depois:.2f} ({fator:.2f}x) {status}"
        )

    linhas.append("\n---\n")

    linhas.append(f"✔ Melhorias: {melhorias}")
    linhas.append(f"❌ Regressões: {regressos}")

    if regressos > 0:
        linhas.append("\n⚠️ Atenção: regressões detectadas!")

    return "\n".join(linhas)


def main():

    if len(sys.argv) < 3:
        print("Uso: python report.py base.csv atual.csv")
        sys.exit(1)

    base = carregar_csv(sys.argv[1])
    atual = carregar_csv(sys.argv[2])

    relatorio = gerar_relatorio(base, atual)

    print(relatorio)


if __name__ == "__main__":
    main()