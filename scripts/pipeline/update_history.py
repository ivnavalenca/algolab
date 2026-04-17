# ============================================================
# SCRIPT: update_history.py
# ============================================================
#
# OBJETIVO:
# Atualizar o histórico de execuções dos algoritmos,
# salvando a execução mais recente (latest.csv) em uma
# pasta de histórico com timestamp.
#
# FUNCIONALIDADES:
# ✔ Cria pasta de histórico automaticamente
# ✔ Evita duplicar execuções iguais
# ✔ Mantém versionamento temporal dos dados
#
# ENTRADA:
# resultados/latest.csv
#
# SAÍDA:
# resultados/historico/run_<timestamp>.csv
#
# ============================================================

import os
import pandas as pd
from datetime import datetime

# ============================================================
# CONFIGURAÇÕES
# ============================================================

LATEST = "resultados/latest.csv"
PASTA_HIST = "resultados/historico"

# ============================================================
# UTILITÁRIOS
# ============================================================

def carregar_csv(path):
    """
    Carrega um CSV de forma segura.
    """

    if not os.path.exists(path):
        print(f"⚠️ Arquivo não encontrado: {path}")
        return None

    try:
        df = pd.read_csv(path)

        if df.empty:
            print(f"⚠️ CSV vazio: {path}")
            return None

        return df

    except Exception as e:
        print(f"❌ Erro ao ler {path}: {e}")
        return None


def gerar_timestamp():
    """
    Gera timestamp para nome do arquivo.
    """
    return datetime.now().strftime("%Y-%m-%d_%H-%M-%S")


def ultima_execucao():
    """
    Retorna o caminho do último arquivo do histórico.
    """

    if not os.path.exists(PASTA_HIST):
        return None

    arquivos = sorted([
        os.path.join(PASTA_HIST, f)
        for f in os.listdir(PASTA_HIST)
        if f.endswith(".csv")
    ])

    return arquivos[-1] if arquivos else None


def eh_igual(df1, df2):
    """
    Verifica se dois DataFrames são iguais.
    """
    try:
        return df1.equals(df2)
    except:
        return False

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("📂 Atualizando histórico...")

    df_atual = carregar_csv(LATEST)

    if df_atual is None:
        print("⚠️ Nenhum latest.csv disponível — pulando histórico.")
        return

    # Garante pasta
    os.makedirs(PASTA_HIST, exist_ok=True)

    ultimo_path = ultima_execucao()

    if ultimo_path:
        df_ultimo = carregar_csv(ultimo_path)

        if df_ultimo is not None and eh_igual(df_atual, df_ultimo):
            print("ℹ️ Execução idêntica à última — não será salva.")
            return

    # Salva nova execução
    timestamp = gerar_timestamp()
    caminho = os.path.join(PASTA_HIST, f"run_{timestamp}.csv")

    df_atual.to_csv(caminho, index=False)

    print(f"📁 Nova execução salva: {caminho}")
    print("✅ Histórico atualizado!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()