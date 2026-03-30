package br.upe.analisealgoritmos.experimentos;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.IOException;

/*
 * ============================================================
 * CLASSE: GraficoUtils
 * ============================================================
 *
 * OBJETIVO:
 *
 * Centralizar a responsabilidade de salvar gráficos.
 *
 * ============================================================
 * POR QUE ISSO É IMPORTANTE?
 *
 * Evita:
 * ❌ repetição de código em todos os experimentos
 *
 * Permite:
 * ✔ reutilização
 * ✔ padronização
 *
 * ============================================================
 */

public class GraficoUtils {

    /*
     * Salva gráfico como imagem PNG
     */
    public static void salvarGrafico(JFreeChart grafico, String nomeArquivo) {

        try {

            /*
             * Cria pasta "resultados" se não existir
             */
            File pasta = new File("resultados");

            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            /*
             * Define caminho do arquivo
             */
            File arquivo = new File("resultados/" + nomeArquivo + ".png");

            /*
             * Salva o gráfico
             */
            ChartUtils.saveChartAsPNG(arquivo, grafico, 800, 600);

            System.out.println("Gráfico salvo em: " + arquivo.getAbsolutePath());

        } catch (IOException e) {

            System.out.println("Erro ao salvar gráfico:");
            e.printStackTrace();
        }
    }
}