package br.upe.analisealgoritmos.utils;

/*
 * ============================================================
 * CLASSE: GeradorGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráficos a partir de arquivos CSV.
 *
 * ============================================================
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class GeradorGrafico {

    /*
     * ============================================================
     * GERAR GRÁFICO
     * ============================================================
     */
    public static void gerar(String caminhoCSV, String caminhoSaida, String titulo) {

        try {

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));

            String linha = br.readLine(); // pular cabeçalho

            while ((linha = br.readLine()) != null) {

                String[] partes = linha.split(",");

                String tamanho = partes[0];
                String algoritmo = partes[2];
                double tempo = Double.parseDouble(partes[3]);

                dataset.addValue(tempo, algoritmo, tamanho);
            }

            br.close();

            JFreeChart chart = ChartFactory.createLineChart(
                    titulo,
                    "Tamanho",
                    "Tempo",
                    dataset
            );

            File arquivo = new File(caminhoSaida);
            arquivo.getParentFile().mkdirs();

            ChartUtils.saveChartAsPNG(arquivo, chart, 800, 600);

            System.out.println("📊 Gráfico gerado: " + caminhoSaida);

        } catch (Exception e) {
            System.err.println("Erro ao gerar gráfico:");
            e.printStackTrace();
        }
    }
}