package br.upe.analisealgoritmos.utils.grafico;

/*
 * ============================================================
 * CLASSE: GeradorGraficoCSV
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráfico de linha a partir de CSV.
 *
 * FORMATO CSV:
 * tamanho,cenario,algoritmo,tempo
 *
 * ============================================================
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GeradorGraficoCSV {

    public static void gerar(String caminhoCSV, String caminhoSaida) {

        try {

            Map<String, XYSeries> series = new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            br.readLine(); // cabeçalho

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                int n = Integer.parseInt(v[0]);
                String algoritmo = v[2];
                double tempo = Double.parseDouble(v[3]);

                series.putIfAbsent(algoritmo, new XYSeries(algoritmo));
                series.get(algoritmo).add(n, tempo);
            }

            br.close();

            XYSeriesCollection dataset = new XYSeriesCollection();
            series.values().forEach(dataset::addSeries);

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Comparação de Algoritmos",
                    "n",
                    "Tempo",
                    dataset
            );

            GraficoUtils.aplicarEstiloXY(chart, dataset);

            ChartUtils.saveChartAsPNG(new File(caminhoSaida), chart, 800, 600);

            System.out.println("📊 Gráfico gerado: " + caminhoSaida);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}