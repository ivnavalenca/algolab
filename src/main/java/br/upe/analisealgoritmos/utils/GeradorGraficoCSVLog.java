package br.upe.analisealgoritmos.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * ============================================================
 * CLASSE: GeradorGraficoCSVLog
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráfico em escala logarítmica para melhor comparação
 * entre algoritmos com diferentes ordens de grandeza.
 *
 * MELHORIAS:
 * ✔ Escala log no eixo Y
 * ✔ Integração com GraficoUtils (estilo e destaque)
 *
 * ============================================================
 */

public class GeradorGraficoCSVLog {

    public static void gerarGraficoLog(String caminhoCSV, String caminhoSaida) {

        try {

            Map<String, XYSeries> seriesMap = new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            String linha;

            // pular cabeçalho
            br.readLine();

            /*
             * ============================================================
             * LEITURA DO CSV
             * formato esperado:
             * tamanho,cenario,algoritmo,tempo
             * ============================================================
             */
            while ((linha = br.readLine()) != null) {

                String[] valores = linha.split(",");

                int tamanho = Integer.parseInt(valores[0]);
                String algoritmo = valores[2];
                double tempo = Double.parseDouble(valores[3]);

                // evita valores zero (log não aceita)
                if (tempo <= 0) continue;

                seriesMap.putIfAbsent(algoritmo, new XYSeries(algoritmo));
                seriesMap.get(algoritmo).add(tamanho, tempo);
            }

            br.close();

            /*
             * ============================================================
             * DATASET
             * ============================================================
             */
            XYSeriesCollection dataset = new XYSeriesCollection();

            for (XYSeries serie : seriesMap.values()) {
                dataset.addSeries(serie);
            }

            /*
             * ============================================================
             * CRIA GRÁFICO
             * ============================================================
             */
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Comparação de Algoritmos (Escala Log)",
                    "Tamanho da Entrada (n)",
                    "Tempo (ns)",
                    dataset
            );

            /*
             * ============================================================
             * APLICA ESCALA LOG NO EIXO Y
             * ============================================================
             */
            XYPlot plot = chart.getXYPlot();

            LogarithmicAxis logAxis = new LogarithmicAxis("Tempo (log ns)");
            plot.setRangeAxis(logAxis);

            /*
             * ============================================================
             * APLICA ESTILO PADRÃO
             * ============================================================
             */
            GraficoUtils.aplicarEstiloXY(chart, dataset);

            /*
             * ============================================================
             * SALVAR
             * ============================================================
             */
            ChartUtils.saveChartAsPNG(
                    new File(caminhoSaida),
                    chart,
                    800,
                    600
            );

            System.out.println("📊 Gráfico LOG gerado em: " + caminhoSaida);

        } catch (Exception e) {
            System.err.println("Erro ao gerar gráfico log: " + e.getMessage());
            e.printStackTrace();
        }
    }
}