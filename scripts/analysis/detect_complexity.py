# ============================================================
# SCRIPT: detect_complexity.py
# ============================================================

import os
import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression

PASTA = "resultados/historico"
SAIDA = "resultados/complexity.csv"

def listar():
    if not os.path.exists(PASTA):
        return []
    return [os.path.join(PASTA, f) for f in os.listdir(PASTA) if f.endswith(".csv")]

def carregar():
    dfs = []
    for arq in listar():
        try:
            df = pd.read_csv(arq)
            if not df.empty:
                dfs.append(df)
        except:
            pass
    return pd.concat(dfs, ignore_index=True) if dfs else None

def ajustar(X, y):
    model = LinearRegression().fit(X, y)
    return model.score(X, y)

def main():

    df = carregar()
    if df is None:
        print("❌ Sem dados")
        return

    resultados = []

    for alg in df["algoritmo"].unique():

        dados = df[df["algoritmo"] == alg]
        grupo = dados.groupby("tamanho")["tempo"].mean().reset_index()

        n = grupo["tamanho"].values.reshape(-1,1)
        t = grupo["tempo"].values

        scores = {
            "O(n)": ajustar(n, t),
            "O(n log n)": ajustar((n * np.log2(n)), t),
            "O(n²)": ajustar((n**2), t)
        }

        melhor = max(scores, key=scores.get)

        resultados.append([alg, melhor, scores[melhor]])

    pd.DataFrame(resultados, columns=["algoritmo", "complexidade", "score"])\
        .to_csv(SAIDA, index=False)

    print(f"📊 {SAIDA}")

if __name__ == "__main__":
    main()