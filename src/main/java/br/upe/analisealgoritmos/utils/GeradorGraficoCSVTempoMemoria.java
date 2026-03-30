package br.upe.analisealgoritmos.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * ============================================================
 * GERADOR DE GRÁFICO (COMPATÍVEL COM CENÁRIOS)
 * ============================================================
 */

public class GeradorGraficoCSVTempoMemoria {

    public static void gerarGraficos(String caminhoCSV, String pastaSaida) {

        gerarGraficoPorCenario(caminhoCSV, pastaSaida, "aleatorio");
    }

    public static void gerarGraficoPorCenario(String caminhoCSV, String pastaSaida, String cenarioFiltro) {

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {

            br.readLine(); // cabeçalho

            Map<String, XYSeries> seriesTempo = new HashMap<>();
            Map<String, XYSeries> seriesMemoria = new HashMap<>();

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                int n = Integer.parseInt(v[0]);
                String cenario = v[1];
                String algoritmo = v[2];
                long tempo = Long.parseLong(v[3]);
                long memoria = Long.parseLong(v[4]);

                /*
                 * FILTRA CENÁRIO
                 */
                if (!cenario.equals(cenarioFiltro)) continue;

                seriesTempo.putIfAbsent(algoritmo, new XYSeries(algoritmo));
                seriesMemoria.putIfAbsent(algoritmo, new XYSeries(algoritmo));

                seriesTempo.get(algoritmo).add(n, tempo);
                seriesMemoria.get(algoritmo).add(n, memoria);
            }

            /*
             * Dataset tempo
             */
            XYSeriesCollection datasetTempo = new XYSeriesCollection();
            seriesTempo.values().forEach(datasetTempo::addSeries);

            /*
             * Dataset memória
             */
            XYSeriesCollection datasetMemoria = new XYSeriesCollection();
            seriesMemoria.values().forEach(datasetMemoria::addSeries);

            /*
             * Gráficos
             */
            JFreeChart graficoTempo = ChartFactory.createXYLineChart(
                    "Tempo (" + cenarioFiltro + ")",
                    "n",
                    "Tempo (ns)",
                    datasetTempo
            );

            JFreeChart graficoMemoria = ChartFactory.createXYLineChart(
                    "Memória (" + cenarioFiltro + ")",
                    "n",
                    "Memória (bytes)",
                    datasetMemoria
            );

            ChartUtils.saveChartAsPNG(
                    new java.io.File(pastaSaida + "/tempo_" + cenarioFiltro + ".png"),
                    graficoTempo,
                    800,
                    600
            );

            ChartUtils.saveChartAsPNG(
                    new java.io.File(pastaSaida + "/memoria_" + cenarioFiltro + ".png"),
                    graficoMemoria,
                    800,
                    600
            );

            System.out.println("Gráficos gerados para cenário: " + cenarioFiltro);

        } catch (Exception e) {
            System.err.println("Erro ao gerar gráficos: " + e.getMessage());
        }
    }
}