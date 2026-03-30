import json
import csv
import os
from datetime import datetime

HISTORY_FILE = "docs/performance_history.json"

def carregar_csv(path):
    dados = {}

    with open(path) as f:
        reader = csv.DictReader(f)
        for row in reader:
            alg = row["algoritmo"]
            tempo = float(row["tempo"])

            if alg not in dados:
                dados[alg] = []

            dados[alg].append(tempo)

    return {k: sum(v)/len(v) for k, v in dados.items()}

def carregar_historico():
    if not os.path.exists(HISTORY_FILE):
        return []
    with open(HISTORY_FILE) as f:
        return json.load(f)

def salvar_historico(hist):
    with open(HISTORY_FILE, "w") as f:
        json.dump(hist, f, indent=2)

def main():
    atual = carregar_csv("resultados/latest.csv")
    historico = carregar_historico()

    entrada = {
        "timestamp": datetime.now().isoformat(),
        "dados": atual
    }

    historico.append(entrada)

    salvar_historico(historico)

if __name__ == "__main__":
    main()