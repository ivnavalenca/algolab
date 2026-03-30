package br.upe.analisealgoritmos.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;

/*
 * ============================================================
 * CLASSE: GeradorGraficoEstatistico
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráfico com média e desvio padrão (barras de erro)
 *
 * MELHORIAS:
 * ✔ Visual estatístico profissional
 * ✔ Uso de desvio padrão
 * ✔ Comparação robusta entre algoritmos
 *
 * ============================================================
 */

public class GeradorGraficoEstatistico {

    public static void gerarGraficoEstatistico(String caminhoCSV, String caminhoSaida) {

        try {

            /*
             * ============================================================
             * ESTRUTURA PARA AGRUPAR DADOS
             * ============================================================
             */
            Map<String, List<Double>> dados = new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            String linha;

            br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                String algoritmo = v[2];
                double tempo = Double.parseDouble(v[3]);

                dados.putIfAbsent(algoritmo, new ArrayList<>());
                dados.get(algoritmo).add(tempo);
            }

            br.close();

            /*
             * ============================================================
             * DATASET ESTATÍSTICO
             * ============================================================
             */
            DefaultStatisticalCategoryDataset dataset =
                    new DefaultStatisticalCategoryDataset();

            for (String alg : dados.keySet()) {

                List<Double> tempos = dados.get(alg);

                double media = tempos.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                double desvio = calcularDesvioPadrao(tempos);

                dataset.add(media, desvio, alg, "Tempo");
            }

            /*
             * ============================================================
             * CRIA GRÁFICO
             * ============================================================
             */
            JFreeChart chart = ChartFactory.createBarChart(
                    "Comparação Estatística de Algoritmos",
                    "Algoritmo",
                    "Tempo (ns)",
                    dataset
            );

            /*
             * ============================================================
             * RENDERER (BARRAS DE ERRO)
             * ============================================================
             */
            CategoryPlot plot = chart.getCategoryPlot();
            StatisticalBarRenderer renderer = new StatisticalBarRenderer();

            plot.setRenderer(renderer);

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

            System.out.println("📊 Gráfico estatístico gerado em: " + caminhoSaida);

        } catch (Exception e) {
            System.err.println("Erro ao gerar gráfico estatístico: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
     * ============================================================
     * CÁLCULO DO DESVIO PADRÃO
     * ============================================================
     */
    private static double calcularDesvioPadrao(List<Double> valores) {

        double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        double soma = 0;

        for (double v : valores) {
            soma += Math.pow(v - media, 2);
        }

        return Math.sqrt(soma / valores.size());
    }
}