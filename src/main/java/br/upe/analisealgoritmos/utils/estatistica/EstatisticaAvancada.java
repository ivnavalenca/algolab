package br.upe.analisealgoritmos.utils.estatistica;

/*
 * ============================================================
 * CLASSE: EstatisticaAvancada
 * ============================================================
 *
 * OBJETIVO:
 * Fornecer métricas estatísticas para análise de algoritmos.
 *
 * MÉTRICAS:
 * ✔ Média
 * ✔ Variância
 * ✔ Desvio padrão
 * ✔ Mediana
 * ✔ Intervalo de confiança (95%)
 * ✔ Teste t
 *
 * ============================================================
 */

import java.util.Arrays;

public class EstatisticaAvancada {

    /*
     * ============================================================
     * MÉDIA
     * ============================================================
     */
    public static double media(double[] valores) {
        return Arrays.stream(valores).average().orElse(0.0);
    }

    /*
     * ============================================================
     * VARIÂNCIA
     * ============================================================
     */
    public static double variancia(double[] valores) {

        double media = media(valores);

        return Arrays.stream(valores)
                .map(v -> Math.pow(v - media, 2))
                .sum() / valores.length;
    }

    /*
     * ============================================================
     * DESVIO PADRÃO
     * ============================================================
     */
    public static double desvioPadrao(double[] valores) {
        return Math.sqrt(variancia(valores));
    }

    /*
     * ============================================================
     * MEDIANA
     * ============================================================
     */
    public static double mediana(double[] valores) {

        double[] copia = Arrays.copyOf(valores, valores.length);
        Arrays.sort(copia);

        int meio = copia.length / 2;

        if (copia.length % 2 == 0) {
            return (copia[meio - 1] + copia[meio]) / 2.0;
        } else {
            return copia[meio];
        }
    }

    /*
     * ============================================================
     * INTERVALO DE CONFIANÇA (95%)
     * ============================================================
     */
    public static double intervaloConfianca95(double[] valores) {

        double desvio = desvioPadrao(valores);
        int n = valores.length;

        return 1.96 * (desvio / Math.sqrt(n));
    }

    /*
     * ============================================================
     * TESTE T
     * ============================================================
     */
    public static double tTest(double[] a, double[] b) {

        double mediaA = media(a);
        double mediaB = media(b);

        double varA = variancia(a);
        double varB = variancia(b);

        int nA = a.length;
        int nB = b.length;

        double numerador = mediaA - mediaB;
        double denominador = Math.sqrt((varA / nA) + (varB / nB));

        if (denominador == 0) return 0;

        return numerador / denominador;
    }
}