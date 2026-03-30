package br.upe.analisealgoritmos.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;

import java.io.BufferedReader;
import java.io.FileReader;

/*
 * ============================================================
 * GRÁFICO ESTATÍSTICO POR CENÁRIO (COM ERRO REAL)
 * ============================================================
 */

public class GeradorGraficoEstatistico {

    public static void gerarTodos(String caminhoCSV, String pasta) {

        gerarPorCenario(caminhoCSV, pasta, "aleatorio");
        gerarPorCenario(caminhoCSV, pasta, "ordenado");
        gerarPorCenario(caminhoCSV, pasta, "reverso");
        gerarPorCenario(caminhoCSV, pasta, "quase");
    }

    public static void gerarPorCenario(String caminhoCSV, String pasta, String cenarioFiltro) {

        DefaultStatisticalCategoryDataset dataset =
                new DefaultStatisticalCategoryDataset();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {

            br.readLine(); // header

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                int n = Integer.parseInt(v[0]);
                String cenario = v[1];
                String algoritmo = v[2];

                double media = Double.parseDouble(v[3]);
                double desvio = Double.parseDouble(v[6]);

                /*
                 * FILTRA CENÁRIO
                 */
                if (!cenario.equals(cenarioFiltro)) continue;

                /*
                 * eixo X = tamanho
                 */
                String categoria = "n=" + n;

                dataset.add(media, desvio, algoritmo, categoria);
            }

            /*
             * CRIA GRÁFICO
             */
            JFreeChart chart = ChartFactory.createBarChart(
                    "Tempo - " + cenarioFiltro + " (Média ± Desvio)",
                    "Tamanho",
                    "Tempo (ns)",
                    dataset
            );

            /*
             * RENDERER COM ERRO REAL
             */
            CategoryPlot plot = chart.getCategoryPlot();
            StatisticalBarRenderer renderer = new StatisticalBarRenderer();
            plot.setRenderer(renderer);

            String caminhoSaida = pasta + "/tempo_" + cenarioFiltro + "_erro.png";

            ChartUtils.saveChartAsPNG(
                    new java.io.File(caminhoSaida),
                    chart,
                    1000,
                    600
            );

            System.out.println("Gráfico gerado: " + caminhoSaida);

        } catch (Exception e) {
            System.err.println("Erro no gráfico: " + e.getMessage());
        }
    }
}