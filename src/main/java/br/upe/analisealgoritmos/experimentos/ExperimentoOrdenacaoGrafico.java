package br.upe.analisealgoritmos.experimentos;

import br.upe.analisealgoritmos.ordenacao.*;
import br.upe.analisealgoritmos.utils.*;

import java.util.Arrays;

/*
 * ============================================================
 * EXPERIMENTO DE ORDENAÇÃO (COM ANÁLISE ESTATÍSTICA)
 * ============================================================
 *
 * ✔ média
 * ✔ mínimo
 * ✔ máximo
 * ✔ desvio padrão
 * ✔ múltiplos cenários
 * ✔ CSV científico
 * ✔ gráfico com barras de erro
 *
 * ============================================================
 */

public class ExperimentoOrdenacaoGrafico {

    private static final int EXECUCOES = 20;

    public static void executar() {

        String pasta = GerenciadorResultados.criarPastaExecucao();
        String caminhoCSV = GerenciadorResultados.caminhoArquivo("ordenacao_estatistico.csv");

        int[] tamanhos = {1000, 2000, 5000, 10000};

        BenchmarkRunner runner = new BenchmarkRunner(EXECUCOES);

        /*
         * ============================================================
         * CSV CIENTÍFICO
         * ============================================================
         */
        CSVExporter.criarArquivo(
                caminhoCSV,
                "n,cenario,algoritmo,media,min,max,desvio,memoria"
        );

        System.out.println("\n=== ORDENAÇÃO (ANÁLISE ESTATÍSTICA) ===");

        for (int n : tamanhos) {

            testarCenario("aleatorio", GeradorVetor.aleatorio(n), n, runner, caminhoCSV);
            testarCenario("ordenado", GeradorVetor.ordenado(n), n, runner, caminhoCSV);
            testarCenario("reverso", GeradorVetor.reverso(n), n, runner, caminhoCSV);
            testarCenario("quase", GeradorVetor.quaseOrdenado(n), n, runner, caminhoCSV);
        }

        System.out.println("\nCSV salvo em: " + caminhoCSV);

        /*
         * ============================================================
         * GRÁFICO ESTATÍSTICO (MÉDIA ± DESVIO)
         * ============================================================
         */
        String caminhoGrafico = pasta + "/estatistico.png";

        GeradorGraficoEstatistico.gerarTodos(caminhoCSV, pasta);

        /*
         * ============================================================
         * INTERPRETAÇÃO
         * ============================================================
         */
        InterpretadorResultados.interpretar(caminhoCSV);

        /*
         * ============================================================
         * PDF
         * ============================================================
         */
        GeradorRelatorioPDF.gerarRelatorio(pasta);

        System.out.println("\nExecução finalizada.");
    }

    /*
     * ============================================================
     * EXECUTA UM CENÁRIO
     * ============================================================
     */
    private static void testarCenario(
            String nome,
            int[] base,
            int n,
            BenchmarkRunner runner,
            String caminhoCSV
    ) {

        System.out.println("\n--- Cenário: " + nome + " | n=" + n + " ---");

        executarAlgoritmo("Bubble", new BubbleSort(), base, nome, n, runner, caminhoCSV);
        executarAlgoritmo("Insertion", new InsertionSort(), base, nome, n, runner, caminhoCSV);
        executarAlgoritmo("Selection", new SelectionSort(), base, nome, n, runner, caminhoCSV);
        executarAlgoritmo("Merge", new MergeSort(), base, nome, n, runner, caminhoCSV);
        executarAlgoritmo("Quick", new QuickSort(), base, nome, n, runner, caminhoCSV);

        executarJavaSort(base, nome, n, runner, caminhoCSV);
    }

    /*
     * ============================================================
     * EXECUTA ALGORITMO COM ESTATÍSTICA
     * ============================================================
     */
    private static void executarAlgoritmo(
            String nome,
            Ordenador algoritmo,
            int[] base,
            String cenario,
            int n,
            BenchmarkRunner runner,
            String caminhoCSV
    ) {

        BenchmarkRunner.Resultado r =
                runner.medir(() -> algoritmo.ordenar(base.clone()));

        long memoria = medirMemoria(() -> algoritmo.ordenar(base.clone()));

        System.out.printf(
                "%-10s | Média: %-10d | Min: %-10d | Max: %-10d | DP: %.2f%n",
                nome, r.media, r.minimo, r.maximo, r.desvioPadrao
        );

        CSVExporter.adicionarLinha(
                caminhoCSV,
                n + "," + cenario + "," + nome + ","
                        + r.media + "," + r.minimo + "," + r.maximo + ","
                        + r.desvioPadrao + "," + memoria
        );
    }

    /*
     * ============================================================
     * JAVA SORT
     * ============================================================
     */
    private static void executarJavaSort(
            int[] base,
            String cenario,
            int n,
            BenchmarkRunner runner,
            String caminhoCSV
    ) {

        BenchmarkRunner.Resultado r =
                runner.medir(() -> Arrays.sort(base.clone()));

        long memoria = medirMemoria(() -> Arrays.sort(base.clone()));

        System.out.printf(
                "%-10s | Média: %-10d | Min: %-10d | Max: %-10d | DP: %.2f%n",
                "JavaSort", r.media, r.minimo, r.maximo, r.desvioPadrao
        );

        CSVExporter.adicionarLinha(
                caminhoCSV,
                n + "," + cenario + ",JavaSort,"
                        + r.media + "," + r.minimo + "," + r.maximo + ","
                        + r.desvioPadrao + "," + memoria
        );
    }

    /*
     * ============================================================
     * MEMÓRIA
     * ============================================================
     */
    private static long medirMemoria(Runnable r) {

        Runtime rt = Runtime.getRuntime();

        System.gc();
        long antes = rt.totalMemory() - rt.freeMemory();

        r.run();

        System.gc();
        long depois = rt.totalMemory() - rt.freeMemory();

        return Math.max(0, depois - antes);
    }
}