import os
import json
from datetime import datetime

# ============================================================
# CONFIGURAÇÃO
# ============================================================
PASTA = "resultados/historico"
SAIDA = "docs/resultados/historico/index.json"

# garante que a pasta existe
os.makedirs(PASTA, exist_ok=True)
os.makedirs(os.path.dirname(SAIDA), exist_ok=True)

# ============================================================
# LISTAR ARQUIVOS CSV
# ============================================================
arquivos = [
    f for f in os.listdir(PASTA)
    if f.endswith(".csv")
]

# ordena por nome (ou data se quiser evoluir)
arquivos.sort()

# ============================================================
# GERAR ESTRUTURA JSON
# ============================================================
lista = []

for nome in arquivos:

    caminho = os.path.join(PASTA, nome)

    # data de modificação (melhor que nome do arquivo)
    timestamp = os.path.getmtime(caminho)
    data = datetime.fromtimestamp(timestamp).strftime("%Y-%m-%d %H:%M")

    lista.append({
        "arquivo": nome,
        "data": data
    })

# ============================================================
# SALVAR JSON
# ============================================================
with open(SAIDA, "w") as f:
    json.dump(lista, f, indent=2)

print("index.json gerado com", len(lista), "arquivos")