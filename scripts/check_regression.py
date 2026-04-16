import os
import sys
from utils import listar_csvs, carregar_dados, media_por_algoritmo

PASTA = "resultados/historico"

arquivos = listar_csvs(PASTA)

if len(arquivos) < 2:
    print("Sem histórico suficiente")
    sys.exit(0)

anterior = media_por_algoritmo(
    carregar_dados(os.path.join(PASTA, arquivos[-2]))
)

atual = media_por_algoritmo(
    carregar_dados(os.path.join(PASTA, arquivos[-1]))
)

limite = 0.15
falhou = False

print("=== Regressão ===")

for alg in atual:

    if alg not in anterior:
        continue

    var = (atual[alg] - anterior[alg]) / anterior[alg]

    print(f"{alg}: {var*100:.2f}%")

    if var > limite:
        print(f"❌ Regressão em {alg}")
        falhou = True

if falhou:
    sys.exit(1)

print("✅ OK")