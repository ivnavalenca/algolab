# ============================================================
# SCRIPT: analyze_regression.py
# ============================================================
#
# OBJETIVO:
# Realizar regressão log-log para estimar a complexidade
# empírica dos algoritmos.
#
# MÉTRICAS:
# ✔ Coeficiente angular (k)
# ✔ Intercepto
# ✔ Coeficiente de determinação (R²)
#
# INTERPRETAÇÃO:
# k ≈ 1   → O(n)
# k ≈ 2   → O(n²)
# k ≈ log → O(n log n)
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# resultados/regression.csv
#
# ============================================================

import os
import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "resultados/regression.csv"

# ============================================================
# UTILITÁRIOS
# ============================================================

def listar_csvs(pasta):
    """
    Lista CSVs do histórico.
    """
    if not os.path.exists(pasta):
        print(f"❌ Pasta não encontrada: {pasta}")
        return []

    return [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]


def carregar_dados(arquivos):
    """
    Carrega e concatena os dados.
    """
    dfs = []

    for arq in arquivos:
        try:
            df = pd.read_csv(arq)

            if df.empty:
                continue

            dfs.append(df)

        except Exception as e:
            print(f"⚠️ Erro ao ler {arq}: {e}")

    if not dfs:
        return None

    return pd.concat(dfs, ignore_index=True)

# ============================================================
# REGRESSÃO LOG-LOG
# ============================================================

def calcular_regressao(df):
    """
    Calcula regressão log-log por algoritmo.
    """

    resultados = []

    algoritmos = df["algoritmo"].unique()

    for alg in algoritmos:

        dados = df[df["algoritmo"] == alg]

        # Agrupa por tamanho (média)
        grupo = dados.groupby("tamanho")["tempo"].mean().reset_index()

        x = np.log(grupo["tamanho"].values).reshape(-1, 1)
        y = np.log(grupo["tempo"].values)

        if len(x) < 2:
            continue

        modelo = LinearRegression()
        modelo.fit(x, y)

        k = modelo.coef_[0]
        intercepto = modelo.intercepto_ if hasattr(modelo, "intercepto_") else modelo.intercept_

        y_pred = modelo.predict(x)

        # R² manual
        ss_res = np.sum((y - y_pred) ** 2)
        ss_tot = np.sum((y - np.mean(y)) ** 2)

        r2 = 1 - (ss_res / ss_tot) if ss_tot != 0 else 0

        resultados.append({
            "algoritmo": alg,
            "k": k,
            "intercepto": intercepto,
            "r2": r2
        })

    return pd.DataFrame(resultados)

# ============================================================
# EXPORTAÇÃO
# ============================================================

def salvar(df):

    os.makedirs("resultados", exist_ok=True)

    df.to_csv(SAIDA, index=False)

    print(f"📁 Regressão salva em: {SAIDA}")

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🔍 Carregando dados...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Nenhum CSV encontrado.")
        return

    df = carregar_dados(arquivos)

    if df is None or df.empty:
        print("❌ Nenhum dado válido.")
        return

    print("📈 Calculando regressão...")

    df_reg = calcular_regressao(df)

    if df_reg.empty:
        print("⚠️ Nenhum resultado gerado.")
        return

    salvar(df_reg)

    print("✅ Regressão concluída!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()