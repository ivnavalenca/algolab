import os
import json

# ============================================================
# PASTA CORRETA (AGORA DENTRO DE docs)
# ============================================================
PASTA = "docs/resultados/historico"

# lista apenas arquivos CSV
arquivos = sorted([
    f for f in os.listdir(PASTA)
    if f.endswith(".csv")
])

# caminho de saída
saida = os.path.join(PASTA, "index.json")

# garante que a pasta existe
os.makedirs(PASTA, exist_ok=True)

# escreve o JSON
with open(saida, "w") as f:
    json.dump(arquivos, f, indent=2)

print("index.json atualizado em docs!")