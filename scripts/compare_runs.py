import csv
import sys

"""
============================================================
SCRIPT: compare_runs.py
============================================================

OBJETIVO:
Comparar duas execuções (CSV) e detectar regressão de performance.

ENTRADA:
python compare_runs.py base.csv atual.csv

SAÍDA:
- Logs no console
- Código de saída (1 se regressão detectada)

============================================================
"""

THRESHOLD = 1.2  # 20% de piora = regressão


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

    # média por algoritmo
    return {
        alg: sum(valores) / len(valores)
        for alg, valores in dados.items()
    }


def detectar_regressao(base, atual):

    regressao_detectada = False

    print("\n📊 Comparação de Performance:\n")

    for algoritmo in base:

        if algoritmo not in atual:
            continue

        antes = base[algoritmo]
        depois = atual[algoritmo]

        fator = depois / antes if antes > 0 else 1

        if fator > THRESHOLD:
            print(f"❌ {algoritmo}: {antes:.2f} → {depois:.2f} ({fator:.2f}x) REGRESSÃO")
            regressao_detectada = True

        elif fator < 0.9:
            print(f"🚀 {algoritmo}: {antes:.2f} → {depois:.2f} ({fator:.2f}x) MELHORIA")

        else:
            print(f"➖ {algoritmo}: {antes:.2f} → {depois:.2f} ({fator:.2f}x) ESTÁVEL")

    return regressao_detectada


def main():

    if len(sys.argv) < 3:
        print("Uso: python compare_runs.py base.csv atual.csv")
        sys.exit(1)

    base_path = sys.argv[1]
    atual_path = sys.argv[2]

    base = carregar_csv(base_path)
    atual = carregar_csv(atual_path)

    regressao = detectar_regressao(base, atual)

    if regressao:
        print("\n❌ Regressão detectada!")
        sys.exit(1)
    else:
        print("\n✅ Nenhuma regressão detectada.")


if __name__ == "__main__":
    main()