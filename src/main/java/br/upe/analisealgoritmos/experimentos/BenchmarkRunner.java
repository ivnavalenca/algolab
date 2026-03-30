package br.upe.analisealgoritmos.experimentos;

import java.util.Arrays;

import br.upe.analisealgoritmos.ordenacao.Ordenador;

/*
 * ============================================================
 * BENCHMARK RUNNER (VERSÃO FINAL COM ESTATÍSTICA)
 * ============================================================
 */

public class BenchmarkRunner {

    private final int execucoes;

    public BenchmarkRunner(int execucoes) {
        this.execucoes = execucoes;
    }

    /*
     * ============================================================
     * RESULTADO ESTATÍSTICO
     * ============================================================
     */
    public static class Resultado {

        public long media;
        public long minimo;
        public long maximo;
        public double desvioPadrao;

        public Resultado(long media, long minimo, long maximo, double desvioPadrao) {
            this.media = media;
            this.minimo = minimo;
            this.maximo = maximo;
            this.desvioPadrao = desvioPadrao;
        }
    }

    /*
     * ============================================================
     * MÉTODO PRINCIPAL
     * ============================================================
     */
    public Resultado medir(Runnable algoritmo) {

        long[] tempos = new long[execucoes];

        for (int i = 0; i < execucoes; i++) {

            long ini = System.nanoTime();
            algoritmo.run();
            tempos[i] = System.nanoTime() - ini;
        }

        Arrays.sort(tempos);

        int inicio = execucoes / 10;
        int fim = execucoes - inicio;

        long soma = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        int count = 0;

        for (int i = inicio; i < fim; i++) {

            long t = tempos[i];

            soma += t;
            min = Math.min(min, t);
            max = Math.max(max, t);

            count++;
        }

        long media = soma / count;

        double somaQuadrados = 0;

        for (int i = inicio; i < fim; i++) {
            double diff = tempos[i] - media;
            somaQuadrados += diff * diff;
        }

        double desvio = Math.sqrt(somaQuadrados / count);

        return new Resultado(media, min, max, desvio);
    }

    /*
     * ============================================================
     * COMPATIBILIDADE COM CÓDIGO ANTIGO
     * ============================================================
     */
    public long medirTempo(Runnable algoritmo) {
        return medir(algoritmo).media;
    }

    public long executarBenchmark(Ordenador algoritmo, int[] vetor) {
        return medirTempo(() -> algoritmo.ordenar(vetor.clone()));
    }

    public long executarBenchmark(Runnable algoritmo, int[] vetor) {
        return medirTempo(algoritmo);
    }
}