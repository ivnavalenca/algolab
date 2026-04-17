package br.upe.analisealgoritmos.utils.grafico;

/*
 * ============================================================
 * CLASSE: GraficoUtils
 * ============================================================
 *
 * OBJETIVO:
 * Padronizar estilo visual dos gráficos.
 *
 * RESPONSABILIDADES:
 * ✔ Definir cores dos algoritmos
 * ✔ Destacar melhor algoritmo
 * ✔ Melhorar legibilidade
 *
 * ============================================================
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficoUtils {

    /*
     * ============================================================
     * PALETA DE CORES
     * ============================================================
     */
    private static final Map<String, Color> CORES = new HashMap<>();

    static {
        CORES.put("Bubble", new Color(192, 57, 43));
        CORES.put("Selection", new Color(230, 126, 34));
        CORES.put("Insertion", new Color(241, 196, 15));
        CORES.put("Merge", new Color(46, 204, 113));
        CORES.put("Quick", new Color(39, 174, 96));
        CORES.put("JavaSort", Color.GRAY);

        CORES.put("Linear", new Color(231, 76, 60));
        CORES.put("Binaria", new Color(52, 152, 219));
    }

    /*
     * ============================================================
     * APLICAR ESTILO
     * ============================================================
     */
    public static void aplicarEstiloXY(JFreeChart chart, XYSeriesCollection dataset) {

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        String melhor = detectarMelhor(dataset);

        for (int i = 0; i < dataset.getSeriesCount(); i++) {

            XYSeries serie = dataset.getSeries(i);
            String nome = serie.getKey().toString();

            boolean destaque = nome.equals(melhor);

            Color cor = CORES.getOrDefault(nome, Color.GRAY);

            renderer.setSeriesPaint(i, destaque ? Color.BLACK : cor);
            renderer.setSeriesStroke(i,
                    destaque ? new BasicStroke(3.0f) : new BasicStroke(1.5f)
            );

            renderer.setSeriesShapesVisible(i, true);
        }

        plot.setRenderer(renderer);

        chart.setTitle(chart.getTitle().getText() + " | Melhor: " + melhor);
    }

    /*
     * ============================================================
     * DETECTAR MELHOR ALGORITMO
     * ============================================================
     */
    private static String detectarMelhor(XYSeriesCollection dataset) {

        String melhor = null;
        double melhorMedia = Double.MAX_VALUE;

        for (int i = 0; i < dataset.getSeriesCount(); i++) {

            XYSeries serie = dataset.getSeries(i);

            double soma = 0;
            int n = serie.getItemCount();

            for (int j = 0; j < n; j++) {
                soma += serie.getY(j).doubleValue();
            }

            double media = soma / n;

            if (media < melhorMedia) {
                melhorMedia = media;
                melhor = serie.getKey().toString();
            }
        }

        return melhor;
    }
}