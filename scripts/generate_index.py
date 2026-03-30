import os
import json

PASTA = "resultados/historico"

arquivos = sorted([
    f for f in os.listdir(PASTA)
    if f.endswith(".csv")
])

saida = "docs/resultados/historico/index.json"

os.makedirs(os.path.dirname(saida), exist_ok=True)

with open(saida, "w") as f:
    json.dump(arquivos, f, indent=2)

print("index.json atualizado!")