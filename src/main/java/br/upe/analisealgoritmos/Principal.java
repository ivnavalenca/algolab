package br.upe.analisealgoritmos;

/*
 * ============================================================
 * CLASSE: Principal
 * ============================================================
 *
 * OBJETIVO:
 * Ponto de entrada da aplicação.
 *
 * FLUXO:
 * 1. Executa experimentos
 * 2. Gera CSV
 * 3. Gera gráficos automaticamente
 *
 * ============================================================
 */

import br.upe.analisealgoritmos.experimentos.ExperimentoBuscaGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoEstruturasGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoOrdenacaoGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoLista03;
import br.upe.analisealgoritmos.utils.GeradorGrafico;

public class Principal {

    /*
     * ============================================================
     * MAIN
     * ============================================================
     */
    public static void main(String[] args) {

        System.out.println("🚀 Iniciando execução dos experimentos...\n");

        try {

            /*
             * ============================================================
             * EXECUTAR EXPERIMENTOS
             * ============================================================
             */
            ExperimentoOrdenacaoGrafico.executar();
            ExperimentoBuscaGrafico.executar();
            ExperimentoEstruturasGrafico.executar();
            ExperimentoLista03.executar();

            /*
             * ============================================================
             * GERAR GRÁFICOS AUTOMATICAMENTE
             * ============================================================
             */
            System.out.println("\n📊 Gerando gráficos...\n");

            GeradorGrafico.gerar(
                    "resultados/ordenacao.csv",
                    "resultados/graficos/ordenacao.png",
                    "Desempenho de Ordenação"
            );

            GeradorGrafico.gerar(
                    "resultados/busca.csv",
                    "resultados/graficos/busca.png",
                    "Desempenho de Busca"
            );

            GeradorGrafico.gerar(
                    "resultados/estruturas.csv",
                    "resultados/graficos/estruturas.png",
                    "Desempenho de Estruturas"
            );

            System.out.println("\n✅ Todos os experimentos finalizados com sucesso!");

        } catch (Exception e) {

            System.err.println("❌ Erro durante execução:");
            e.printStackTrace();
        }
    }
}