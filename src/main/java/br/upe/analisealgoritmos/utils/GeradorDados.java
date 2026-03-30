package br.upe.analisealgoritmos.utils;

/*
 * ============================================================
 * CLASSE: GeradorDados
 * ============================================================
 *
 * OBJETIVO:
 * Gerar dados de entrada para experimentos.
 *
 * TIPOS:
 * - Vetor aleatório
 * - Vetor ordenado
 * - Vetor reverso
 *
 * ============================================================
 */

import java.util.Random;

public class GeradorDados {

    /*
     * ============================================================
     * RANDOM FIXO (REPRODUTIBILIDADE)
     * ============================================================
     */
    private static final Random RANDOM = new Random(42);

    /*
     * ============================================================
     * GERAR VETOR ALEATÓRIO
     * ============================================================
     */
    public static int[] gerarVetorAleatorio(int n) {

        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = RANDOM.nextInt(100000);
        }

        return vetor;
    }

    /*
     * ============================================================
     * GERAR VETOR ORDENADO
     * ============================================================
     */
    public static int[] gerarVetorOrdenado(int n) {

        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = i;
        }

        return vetor;
    }

    /*
     * ============================================================
     * GERAR VETOR REVERSO
     * ============================================================
     */
    public static int[] gerarVetorReverso(int n) {

        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = n - i;
        }

        return vetor;
    }
}