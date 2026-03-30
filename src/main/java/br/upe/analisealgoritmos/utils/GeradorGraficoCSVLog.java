package br.upe.analisealgoritmos.utils;

import java.io.BufferedReader;
import java.io.FileReader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * ============================================================
 * GERADOR DE GRÁFICO COM ESCALA LOGARÍTMICA
 * ============================================================
 *
 * OBJETIVO:
 * Melhorar a visualização quando há grande diferença entre
 * algoritmos (ex: O(n²) vs O(n log n)).
 *
 * ============================================================
 * POR QUE USAR ESCALA LOG?
 *
 * Em escala linear:
 * ❌ algoritmos rápidos ficam "achatados"
 *
 * Em escala log:
 * ✔ diferenças ficam visíveis
 *
 * ============================================================
 * RESULTADO:
 *
 * ✔ Comparação mais justa
 * ✔ Melhor análise visual
 *
 * ============================================================
 */

public class GeradorGraficoCSVLog {

    public static void gerarGraficoLog(String caminhoCSV, String caminhoSaida) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {

            /*
             * Lê cabeçalho
             */
            String linha = br.readLine();
            String[] nomes = linha.split(",");

            XYSeries[] series = new XYSeries[nomes.length - 1];

            for (int i = 1; i < nomes.length; i++) {
                series[i - 1] = new XYSeries(nomes[i]);
            }

            /*
             * Lê dados
             */
            while ((linha = br.readLine()) != null) {

                String[] valores = linha.split(",");

                int n = Integer.parseInt(valores[0]);

                for (int i = 1; i < valores.length; i++) {

                    long tempo = Long.parseLong(valores[i]);

                    /*
                     * Evita erro log(0)
                     */
                    if (tempo <= 0) tempo = 1;

                    series[i - 1].add(n, tempo);
                }
            }

            for (XYSeries s : series) {
                dataset.addSeries(s);
            }

        } catch (Exception e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return;
        }

        /*
         * Cria gráfico
         */
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Comparação de Algoritmos (Escala Logarítmica)",
                "Tamanho (n)",
                "Tempo (ns)",
                dataset
        );

        /*
         * ============================================================
         * AQUI ESTÁ A DIFERENÇA PRINCIPAL
         * ============================================================
         */
        XYPlot plot = chart.getXYPlot();

        LogarithmicAxis logAxis = new LogarithmicAxis("Tempo (ns)");
        plot.setRangeAxis(logAxis);

        try {
            ChartUtils.saveChartAsPNG(
                    new java.io.File(caminhoSaida),
                    chart,
                    800,
                    600
            );

            System.out.println("Gráfico LOG gerado em: " + caminhoSaida);

        } catch (Exception e) {
            System.err.println("Erro ao salvar gráfico: " + e.getMessage());
        }
    }
}