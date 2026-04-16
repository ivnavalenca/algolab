package br.upe.analisealgoritmos.experimentos;

import java.util.ArrayList;
import java.util.List;

import br.upe.analisealgoritmos.listas.lista03.Q1_BuscaMatriz;
import br.upe.analisealgoritmos.listas.lista03.Q3_f2_Quadratico;
import br.upe.analisealgoritmos.listas.lista03.Q4_Intersecao;
import br.upe.analisealgoritmos.utils.CSVExporter;

/*
 * ============================================================
 * EXPERIMENTO — LISTA 03
 * ============================================================
 *
 * OBJETIVO:
 * - Executar algoritmos da lista 03
 * - Medir desempenho com BenchmarkRunner
 * - Gerar CSV
 * - Salvar histórico automaticamente
 *
 * ============================================================
 */

public class ExperimentoLista03 {

    public static void executar() {

        System.out.println("🚀 Executando Lista 03...");

        int[] tamanhos = {100, 500, 1000, 2000};

        List<String[]> resultados = new ArrayList<>();

        // 🔥 Benchmark profissional
        BenchmarkRunner runner = new BenchmarkRunner(20);

        for (int n : tamanhos) {

            /*
             * ========================================================
             * Q1 — Melhor caso (Θ(1))
             * ========================================================
             */
            long t1 = runner.medirTempo(() ->
                    Q1_BuscaMatriz.executarMelhorCaso(n)
            );

            resultados.add(new String[]{
                    String.valueOf(n), "lista03", "Q1_Melhor", String.valueOf(t1)
            });

            /*
             * ========================================================
             * Q1 — Pior caso (Θ(N²))
             * ========================================================
             */
            long t2 = runner.medirTempo(() ->
                    Q1_BuscaMatriz.executarPiorCaso(n)
            );

            resultados.add(new String[]{
                    String.valueOf(n), "lista03", "Q1_Pior", String.valueOf(t2)
            });

            /*
             * ========================================================
             * Q3 — Quadrático (Θ(N²))
             * ========================================================
             */
            long t3 = runner.medirTempo(() ->
                    Q3_f2_Quadratico.executar(n)
            );

            resultados.add(new String[]{
                    String.valueOf(n), "lista03", "Q3_Quadratico", String.valueOf(t3)
            });

            /*
             * ========================================================
             * Q4 — Linear otimizado (Θ(N))
             * ========================================================
             */
            long t4 = runner.medirTempo(() ->
                    Q4_Intersecao.executar(n)
            );

            resultados.add(new String[]{
                    String.valueOf(n), "lista03", "Q4_Intersecao", String.valueOf(t4)
            });
        }

        /*
         * ============================================================
         * EXPORTAÇÃO
         * ============================================================
         */
        CSVExporter.salvar("resultados/lista03.csv", resultados);

        CSVExporter.salvarComHistorico(
                "resultados",
                "lista03",
                resultados
        );

        System.out.println("📁 CSV salvo em: resultados/lista03.csv");
        System.out.println("📁 Histórico atualizado!");
        System.out.println("✅ Lista 03 finalizada!");
    }
}