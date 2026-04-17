# ============================================================
# SCRIPT: plot_interactive.py
# ============================================================

import os
import pandas as pd
import plotly.express as px

PASTA = "resultados/historico"
SAIDA = "docs/assets/interactive.html"

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

def main():

    df = carregar()
    if df is None:
        print("❌ Sem dados")
        return

    fig = px.line(
        df,
        x="tamanho",
        y="tempo",
        color="algoritmo",
        title="Comparação Interativa"
    )

    os.makedirs("docs/assets", exist_ok=True)
    fig.write_html(SAIDA)

    print(f"🌐 {SAIDA}")

if __name__ == "__main__":
    main()