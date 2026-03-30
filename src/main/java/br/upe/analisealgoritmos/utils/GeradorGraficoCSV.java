package br.upe.analisealgoritmos.utils;

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

/*
 * ============================================================
 * CLASSE: GeradorGraficoCSV
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráfico a partir de arquivo CSV contendo resultados de benchmark.
 *
 * MELHORIAS IMPLEMENTADAS:
 * ✔ Estilo padronizado (GraficoUtils)
 * ✔ Destaque automático do melhor algoritmo
 * ✔ Código mais limpo e reutilizável
 *
 * ============================================================
 */

public class GeradorGraficoCSV {

    public static void gerarGrafico(String caminhoCSV, String caminhoSaida) {

        try {

            Map<String, XYSeries> seriesMap = new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            String linha;

            // Pular cabeçalho
            br.readLine();

            /*
             * ============================================================
             * LEITURA DO CSV
             * Esperado:
             * tamanho,cenario,algoritmo,tempo
             * ============================================================
             */
            while ((linha = br.readLine()) != null) {

                String[] valores = linha.split(",");

                int tamanho = Integer.parseInt(valores[0]);
                String algoritmo = valores[2];
                double tempo = Double.parseDouble(valores[3]);

                // Cria série se não existir
                seriesMap.putIfAbsent(algoritmo, new XYSeries(algoritmo));

                // Adiciona ponto
                seriesMap.get(algoritmo).add(tamanho, tempo);
            }

            br.close();

            /*
             * ============================================================
             * MONTA DATASET
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
                    "Comparação de Algoritmos",
                    "Tamanho da Entrada (n)",
                    "Tempo (ns)",
                    dataset
            );

            /*
             * ============================================================
             * 🔥 AQUI ESTÁ A MELHORIA PRINCIPAL
             * ============================================================
             */
            GraficoUtils.aplicarEstiloXY(chart, dataset);

            /*
             * ============================================================
             * SALVA IMAGEM
             * ============================================================
             */
            ChartUtils.saveChartAsPNG(
                    new File(caminhoSaida),
                    chart,
                    800,
                    600
            );

            System.out.println("📊 Gráfico gerado em: " + caminhoSaida);

        } catch (Exception e) {
            System.err.println("Erro ao gerar gráfico: " + e.getMessage());
            e.printStackTrace();
        }
    }
}