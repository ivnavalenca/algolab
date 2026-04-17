# ============================================================
# SCRIPT: main_pipeline.py
# ============================================================
#
# OBJETIVO:
# Executar toda a pipeline do projeto Algolab de forma
# automatizada.
#
# ETAPAS:
# ✔ Atualização do histórico
# ✔ Análise de dados
# ✔ Geração de gráficos
# ✔ Geração de badges
# ✔ Geração de relatórios
#
# USO:
# python scripts/main_pipeline.py
#
# ============================================================

import subprocess
import sys

# ============================================================
# EXECUTOR DE SCRIPTS
# ============================================================

def run(script):
    """
    Executa um script Python e exibe status.
    """
    print(f"\n🚀 Executando: {script}")

    try:
        subprocess.run([sys.executable, script], check=True)
    except subprocess.CalledProcessError:
        print(f"❌ Erro ao executar: {script}")

# ============================================================
# PIPELINE PRINCIPAL
# ============================================================

def main():

    print("🔬 Iniciando pipeline Algolab...")

    # ========================================================
    # 1. HISTÓRICO (opcional)
    # ========================================================
    run("scripts/pipeline/update_history.py")

    # ========================================================
    # 2. ANÁLISE
    # ========================================================
    run("scripts/analysis/compute_metrics.py")
    run("scripts/analysis/compare_runs.py")
    run("scripts/analysis/analyze_regression.py")
    run("scripts/analysis/compute_ranking.py")

    # ========================================================
    # 3. VISUALIZAÇÃO
    # ========================================================
    run("scripts/visualization/plot_boxplot.py")
    run("scripts/visualization/plot_line_chart.py")
    run("scripts/visualization/plot_comparison_chart.py")
    run("scripts/visualization/plot_history_chart.py")
    run("scripts/visualization/plot_readme_chart.py")

    # 🧠 NOVO: comparação teórica vs real
    run("scripts/visualization/plot_complexity_comparison.py")

    # ========================================================
    # 4. BADGES
    # ========================================================
    run("scripts/badges/generate_summary_badge.py")
    run("scripts/badges/generate_best_badge.py")
    run("scripts/badges/generate_quality_badge.py")
    run("scripts/badges/generate_trend_badge.py")
    run("scripts/badges/generate_project_score_badge.py")

    # ========================================================
    # 5. RELATÓRIOS
    # ========================================================
    run("scripts/reports/generate_report.py")
    run("scripts/reports/generate_index.py")

    print("\n🎉 Pipeline concluída com sucesso!")

# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    main()