package br.upe.analisealgoritmos.utils.grafico;

/*
 * ============================================================
 * CLASSE: GeradorGraficoEstatistico
 * ============================================================
 *
 * OBJETIVO:
 * Gerar gráfico com média e desvio padrão (barras de erro)
 *
 * ============================================================
 */

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

public class GeradorGraficoEstatistico {

    public static void gerar(String caminhoCSV, String caminhoSaida) {

        try {

            Map<String, List<Double>> dados = new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            br.readLine();

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                String algoritmo = v[2];
                double tempo = Double.parseDouble(v[3]);

                dados.putIfAbsent(algoritmo, new ArrayList<>());
                dados.get(algoritmo).add(tempo);
            }

            br.close();

            DefaultStatisticalCategoryDataset dataset =
                    new DefaultStatisticalCategoryDataset();

            for (String alg : dados.keySet()) {

                List<Double> tempos = dados.get(alg);

                double media = tempos.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                double desvio = calcularDesvio(tempos);

                dataset.add(media, desvio, alg, "Tempo");
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "Comparação Estatística",
                    "Algoritmo",
                    "Tempo",
                    dataset
            );

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRenderer(new StatisticalBarRenderer());

            ChartUtils.saveChartAsPNG(new File(caminhoSaida), chart, 800, 600);

            System.out.println("📊 Gráfico estatístico gerado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double calcularDesvio(List<Double> valores) {

        double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        double soma = 0;

        for (double v : valores) {
            soma += Math.pow(v - media, 2);
        }

        return Math.sqrt(soma / valores.size());
    }
}