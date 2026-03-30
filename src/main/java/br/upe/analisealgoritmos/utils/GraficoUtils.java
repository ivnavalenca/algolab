package br.upe.analisealgoritmos.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * ============================================================
 * UTILITÁRIO DE ESTILO DE GRÁFICOS
 * ============================================================
 *
 * RESPONSABILIDADES:
 *
 * ✔ Padronizar cores dos algoritmos
 * ✔ Destacar automaticamente o melhor algoritmo
 * ✔ Melhorar legibilidade dos gráficos
 * ✔ Criar identidade visual consistente
 *
 * ============================================================
 */

public class GraficoUtils {

    /*
     * ============================================================
     * PALETA DE CORES PADRÃO
     * ============================================================
     */
    private static final Map<String, Color> CORES = new HashMap<>();

    static {
        CORES.put("Bubble", new Color(192, 57, 43));      // vermelho
        CORES.put("Selection", new Color(230, 126, 34));  // laranja
        CORES.put("Insertion", new Color(241, 196, 15));  // amarelo
        CORES.put("Merge", new Color(46, 204, 113));      // verde
        CORES.put("Quick", new Color(39, 174, 96));       // verde escuro
        CORES.put("JavaSort", Color.GRAY);

        CORES.put("Linear", new Color(231, 76, 60));
        CORES.put("Binaria", new Color(52, 152, 219));
    }

    /*
     * ============================================================
     * APLICA ESTILO AO GRÁFICO XY
     * ============================================================
     */
    public static void aplicarEstiloXY(JFreeChart chart, XYSeriesCollection dataset) {

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // 🔥 Descobrir melhor algoritmo (menor média)
        String melhor = detectarMelhor(dataset);

        /*
         * ============================================================
         * APLICA ESTILO EM CADA SÉRIE
         * ============================================================
         */
        for (int i = 0; i < dataset.getSeriesCount(); i++) {

            XYSeries serie = dataset.getSeries(i);
            String nome = serie.getKey().toString();

            boolean isMelhor = nome.equals(melhor);

            // Cor
            Color cor = CORES.getOrDefault(nome, Color.GRAY);
            renderer.setSeriesPaint(i, isMelhor ? Color.BLACK : cor);

            // Espessura da linha
            renderer.setSeriesStroke(i,
                    isMelhor ? new BasicStroke(3.5f) : new BasicStroke(1.5f)
            );

            // Mostrar pontos
            renderer.setSeriesShapesVisible(i, true);
        }

        plot.setRenderer(renderer);

        // 🧠 Atualiza título com melhor algoritmo
        chart.setTitle(chart.getTitle().getText() + " | Melhor: " + melhor);
    }

    /*
     * ============================================================
     * DETECTA O MELHOR ALGORITMO
     * ============================================================
     */
    private static String detectarMelhor(XYSeriesCollection dataset) {

        String melhor = null;
        double melhorMedia = Double.MAX_VALUE;

        for (int i = 0; i < dataset.getSeriesCount(); i++) {

            XYSeries serie = dataset.getSeries(i);

            double soma = 0;
            int count = serie.getItemCount();

            for (int j = 0; j < count; j++) {
                soma += serie.getY(j).doubleValue();
            }

            double media = soma / count;

            if (media < melhorMedia) {
                melhorMedia = media;
                melhor = serie.getKey().toString();
            }
        }

        return melhor;
    }
}