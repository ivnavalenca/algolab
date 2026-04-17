package br.upe.analisealgoritmos.utils.grafico;

/*
 * ============================================================
 * CLASSE: GeradorGraficoCSVTempoMemoria
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráficos separados de tempo e memória por cenário
 *
 * FORMATO CSV:
 * tamanho,cenario,algoritmo,tempo,memoria
 *
 * ============================================================
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GeradorGraficoCSVTempoMemoria {

    public static void gerar(String caminhoCSV, String pastaSaida, String cenarioFiltro) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            br.readLine();

            Map<String, XYSeries> tempo = new HashMap<>();
            Map<String, XYSeries> memoria = new HashMap<>();

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                int n = Integer.parseInt(v[0]);
                String cenario = v[1];
                String algoritmo = v[2];
                long t = Long.parseLong(v[3]);
                long m = Long.parseLong(v[4]);

                if (!cenario.equals(cenarioFiltro)) continue;

                tempo.putIfAbsent(algoritmo, new XYSeries(algoritmo));
                memoria.putIfAbsent(algoritmo, new XYSeries(algoritmo));

                tempo.get(algoritmo).add(n, t);
                memoria.get(algoritmo).add(n, m);
            }

            XYSeriesCollection datasetTempo = new XYSeriesCollection();
            tempo.values().forEach(datasetTempo::addSeries);

            XYSeriesCollection datasetMemoria = new XYSeriesCollection();
            memoria.values().forEach(datasetMemoria::addSeries);

            JFreeChart chartTempo = ChartFactory.createXYLineChart(
                    "Tempo - " + cenarioFiltro, "n", "Tempo", datasetTempo
            );

            JFreeChart chartMemoria = ChartFactory.createXYLineChart(
                    "Memória - " + cenarioFiltro, "n", "Memória", datasetMemoria
            );

            ChartUtils.saveChartAsPNG(
                    new java.io.File(pastaSaida + "/tempo.png"), chartTempo, 800, 600
            );

            ChartUtils.saveChartAsPNG(
                    new java.io.File(pastaSaida + "/memoria.png"), chartMemoria, 800, 600
            );

            System.out.println("📊 Gráficos tempo/memória gerados");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}