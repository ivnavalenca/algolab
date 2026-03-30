package br.upe.analisealgoritmos.experimentos;

import java.io.File;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.upe.analisealgoritmos.grafos.BFS;
import br.upe.analisealgoritmos.grafos.DFS;
import br.upe.analisealgoritmos.grafos.Dijkstra;
import br.upe.analisealgoritmos.grafos.Grafo;
import br.upe.analisealgoritmos.utils.GerenciadorResultados;
import br.upe.analisealgoritmos.utils.GraficoUtils;

/*
 * ============================================================
 * CLASSE: ExperimentoGrafosGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Comparar desempenho de algoritmos em grafos:
 * ✔ BFS
 * ✔ DFS
 * ✔ Dijkstra
 *
 * ============================================================
 */

public class ExperimentoGrafosGrafico {

    public static void executar() {

        int[] tamanhos = {100, 500, 1000, 2000};

        XYSeries bfsSeries = new XYSeries("BFS");
        XYSeries dfsSeries = new XYSeries("DFS");
        XYSeries dijkstraSeries = new XYSeries("Dijkstra");

        for (int n : tamanhos) {

            Grafo grafo = gerarGrafo(n, n * 2);

            long tempoBFS = medir(() -> BFS.executar(grafo, 0));
            long tempoDFS = medir(() -> DFS.executar(grafo, 0));

            int[][] matriz = gerarMatrizAdjacencia(n);
            long tempoDijkstra = medir(() -> Dijkstra.executar(matriz, 0));

            bfsSeries.add(n, tempoBFS);
            dfsSeries.add(n, tempoDFS);
            dijkstraSeries.add(n, tempoDijkstra);

            System.out.println("✔ n=" + n + " concluído");
        }

        /*
         * ============================================================
         * DATASET
         * ============================================================
         */
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(bfsSeries);
        dataset.addSeries(dfsSeries);
        dataset.addSeries(dijkstraSeries);

        /*
         * ============================================================
         * GRÁFICO
         * ============================================================
         */
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Algoritmos em Grafos (BFS vs DFS vs Dijkstra)",
                "Número de vértices (V)",
                "Tempo (ns)",
                dataset
        );

        // 🔥 estilo avançado
        GraficoUtils.aplicarEstiloXY(chart, dataset);

        /*
         * ============================================================
         * SALVAR
         * ============================================================
         */
        try {

            String caminho = GerenciadorResultados.caminhoArquivo("grafos.png");

            ChartUtils.saveChartAsPNG(
                    new File(caminho),
                    chart,
                    800,
                    600
            );

            System.out.println("📊 Gráfico de grafos salvo em: " + caminho);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * ============================================================
     * MEDIÇÃO DE TEMPO
     * ============================================================
     */
    private static long medir(Runnable r) {

        long inicio = System.nanoTime();
        r.run();
        long fim = System.nanoTime();

        return fim - inicio;
    }

    /*
     * ============================================================
     * GERA GRAFO ALEATÓRIO
     * ============================================================
     */
    private static Grafo gerarGrafo(int V, int E) {

        Grafo g = new Grafo(V);
        Random rand = new Random(42); // reprodutível

        for (int i = 0; i < E; i++) {
            int origem = rand.nextInt(V);
            int destino = rand.nextInt(V);

            g.adicionarAresta(origem, destino);
        }

        return g;
    }

    /*
     * ============================================================
     * GERA MATRIZ PARA DIJKSTRA
     * ============================================================
     */
    private static int[][] gerarMatrizAdjacencia(int V) {

        int[][] matriz = new int[V][V];
        Random rand = new Random(42);

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && rand.nextDouble() < 0.3) {
                    matriz[i][j] = rand.nextInt(10) + 1;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }

        return matriz;
    }
}