# ============================================================
# SCRIPT: compare_runs.py
# ============================================================
#
# OBJETIVO:
# Comparar execuções históricas dos algoritmos e identificar
# evolução de desempenho ao longo do tempo.
#
# FUNCIONALIDADES:
# ✔ Agrupa execuções por algoritmo
# ✔ Calcula média por execução
# ✔ Compara execuções recentes vs antigas
#
# ENTRADA:
# resultados/historico/*.csv
#
# SAÍDA:
# resultados/comparison.csv
#
# ============================================================

import os
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================

PASTA = "resultados/historico"
SAIDA = "resultados/comparison.csv"

# ============================================================
# UTILITÁRIOS
# ============================================================

def listar_csvs(pasta):
    """
    Lista todos os arquivos CSV do histórico.
    """
    if not os.path.exists(pasta):
        print(f"❌ Pasta não encontrada: {pasta}")
        return []

    arquivos = [
        os.path.join(pasta, f)
        for f in os.listdir(pasta)
        if f.endswith(".csv")
    ]

    return sorted(arquivos)


def carregar_execucoes(arquivos):
    """
    Carrega cada execução como um DataFrame separado.
    """
    execucoes = []

    for arq in arquivos:
        try:
            df = pd.read_csv(arq)

            if df.empty:
                continue

            execucoes.append((arq, df))

        except Exception as e:
            print(f"⚠️ Erro ao ler {arq}: {e}")

    return execucoes

# ============================================================
# COMPARAÇÃO
# ============================================================

def comparar_execucoes(execucoes):
    """
    Compara execuções consecutivas.
    """

    resultados = []

    if len(execucoes) < 2:
        print("⚠️ Poucas execuções para comparar.")
        return resultados

    for i in range(1, len(execucoes)):

        nome_anterior, df_ant = execucoes[i - 1]
        nome_atual, df_atual = execucoes[i]

        # Média por algoritmo
        media_ant = df_ant.groupby("algoritmo")["tempo"].mean()
        media_atual = df_atual.groupby("algoritmo")["tempo"].mean()

        algoritmos = set(media_ant.index).intersection(set(media_atual.index))

        for alg in algoritmos:

            tempo_ant = media_ant[alg]
            tempo_atual = media_atual[alg]

            variacao = tempo_atual - tempo_ant
            percentual = (variacao / tempo_ant) * 100 if tempo_ant != 0 else 0

            status = "igual"

            if percentual < -5:
                status = "melhorou"
            elif percentual > 5:
                status = "piorou"

            resultados.append({
                "algoritmo": alg,
                "execucao_anterior": nome_anterior,
                "execucao_atual": nome_atual,
                "tempo_anterior": tempo_ant,
                "tempo_atual": tempo_atual,
                "variacao": variacao,
                "percentual": percentual,
                "status": status
            })

    return pd.DataFrame(resultados)

# ============================================================
# EXPORTAÇÃO
# ============================================================

def salvar(df):

    os.makedirs("resultados", exist_ok=True)

    df.to_csv(SAIDA, index=False)

    print(f"📁 Comparação salva em: {SAIDA}")

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🔍 Carregando execuções...")

    arquivos = listar_csvs(PASTA)

    if not arquivos:
        print("❌ Nenhum CSV encontrado.")
        return

    execucoes = carregar_execucoes(arquivos)

    if not execucoes:
        print("❌ Nenhuma execução válida.")
        return

    print("📊 Comparando execuções...")

    df_comp = comparar_execucoes(execucoes)

    if df_comp.empty:
        print("⚠️ Nenhuma comparação gerada.")
        return

    salvar(df_comp)

    print("✅ Comparação concluída!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()