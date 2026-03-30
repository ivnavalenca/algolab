package br.upe.analisealgoritmos.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * ============================================================
 * GERADOR DE GRÁFICO A PARTIR DE CSV
 * ============================================================
 *
 * OBJETIVO:
 * Ler arquivo CSV e gerar gráfico automaticamente.
 *
 * ============================================================
 * FORMATO DO CSV:
 *
 * n,Bubble,Insertion,Selection,Merge,Quick,JavaSort
 *
 * ============================================================
 * RESULTADO:
 *
 * ✔ Geração automática de gráfico PNG
 * ✔ Visualização comparativa dos algoritmos
 *
 * ============================================================
 */

public class GeradorGraficoCSV {

    public static void gerarGrafico(String caminhoCSV, String caminhoSaida) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {

            /*
             * Lê cabeçalho (nomes das colunas)
             */
            String linha = br.readLine();
            String[] nomes = linha.split(",");

            /*
             * Cria uma série para cada algoritmo
             */
            XYSeries[] series = new XYSeries[nomes.length - 1];

            for (int i = 1; i < nomes.length; i++) {
                series[i - 1] = new XYSeries(nomes[i]);
            }

            /*
             * Lê dados do CSV
             */
            while ((linha = br.readLine()) != null) {

                String[] valores = linha.split(",");

                int n = Integer.parseInt(valores[0]);

                for (int i = 1; i < valores.length; i++) {

                    long tempo = Long.parseLong(valores[i]);

                    series[i - 1].add(n, tempo);
                }
            }

            /*
             * Adiciona todas as séries ao dataset
             */
            for (XYSeries s : series) {
                dataset.addSeries(s);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return;
        }

        /*
         * Cria gráfico de linhas
         */
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Comparação de Algoritmos de Ordenação",
                "Tamanho (n)",
                "Tempo (ns)",
                dataset
        );

        try {
            ChartUtils.saveChartAsPNG(
                    new java.io.File(caminhoSaida),
                    chart,
                    800,
                    600
            );

            System.out.println("Gráfico gerado em: " + caminhoSaida);

        } catch (IOException e) {
            System.err.println("Erro ao salvar gráfico: " + e.getMessage());
        }
    }
}