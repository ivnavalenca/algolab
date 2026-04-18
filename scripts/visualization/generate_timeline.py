# ============================================================
# MÓDULO: generate_timeline
# ============================================================
#
# OBJETIVO:
# Manter histórico de execuções do pipeline AlgoLab.
#
# DESCRIÇÃO:
# A cada execução:
# ✔ lê resultados atuais
# ✔ calcula métricas (tempo médio)
# ✔ adiciona ao histórico
#
# SAÍDA:
# - docs/resultados/timeline.csv
#
# USO:
# ✔ alimentar history_chart
# ✔ acompanhar evolução do projeto
#
# ============================================================

import os
import pandas as pd
from datetime import datetime

# ============================================================
# CONFIGURAÇÃO
# ============================================================

TIMELINE_PATH = "docs/resultados/timeline.csv"


# ============================================================
# GARANTIR DIRETÓRIO
# ============================================================
def garantir_diretorio():
    os.makedirs("docs/resultados", exist_ok=True)


# ============================================================
# CARREGAR TIMELINE EXISTENTE
# ============================================================
def carregar_timeline():
    if os.path.exists(TIMELINE_PATH):
        return pd.read_csv(TIMELINE_PATH)
    else:
        return pd.DataFrame(columns=[
            "timestamp",
            "tempo_medio"
        ])


# ============================================================
# CALCULAR MÉTRICA ATUAL
# ============================================================
def calcular_tempo_medio(df):
    """
    Calcula tempo médio global dos algoritmos.
    """
    return df["tempo"].mean()


# ============================================================
# ATUALIZAR TIMELINE
# ============================================================
def atualizar_timeline(df_resultados):
    """
    Atualiza o histórico com a execução atual.
    """

    garantir_diretorio()

    timeline = carregar_timeline()

    tempo_medio = calcular_tempo_medio(df_resultados)

    nova_linha = {
        "timestamp": datetime.now().isoformat(),
        "tempo_medio": tempo_medio
    }

    timeline = pd.concat([timeline, pd.DataFrame([nova_linha])], ignore_index=True)

    timeline.to_csv(TIMELINE_PATH, index=False)

    print(f"📈 timeline atualizada: {TIMELINE_PATH}")

    return timeline


# ============================================================
# PREPARAR DADOS PARA GRÁFICO
# ============================================================
def preparar_dados_timeline(timeline_df):
    """
    Converte timeline em dados para gráfico.
    """

    x = list(range(1, len(timeline_df) + 1))
    y = timeline_df["tempo_medio"].tolist()

    return x, y