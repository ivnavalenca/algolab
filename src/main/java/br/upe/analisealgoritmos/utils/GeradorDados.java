package br.upe.analisealgoritmos.utils;

import java.util.Random;

/*
 * ============================================================
 * CLASSE: GERADOR DE DADOS
 * ============================================================
 *
 * OBJETIVO:
 *
 * Centralizar a geração de dados para os experimentos.
 *
 * ============================================================
 * POR QUE ISSO É IMPORTANTE?
 *
 * Evita:
 * ❌ repetição de código
 * ❌ inconsistência entre experimentos
 *
 * Permite:
 * ✔ reutilização
 * ✔ comparação justa
 *
 * ============================================================
 */

public class GeradorDados {

    /*
     * Gerador aleatório reutilizável
     */
    private static final Random random = new Random();

    /*
     * ============================================================
     * VETOR ALEATÓRIO (CASO MÉDIO)
     * ============================================================
     *
     * Representa cenário real
     */
    public static int[] vetorAleatorio(int n) {

        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = random.nextInt(n);
        }

        return v;
    }

    /*
     * ============================================================
     * VETOR ORDENADO (MELHOR CASO)
     * ============================================================
     *
     * Útil para:
     * - InsertionSort
     * - BubbleSort (com otimização)
     */
    public static int[] vetorOrdenado(int n) {

        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = i;
        }

        return v;
    }

    /*
     * ============================================================
     * VETOR INVERTIDO (PIOR CASO)
     * ============================================================
     *
     * Útil para:
     * - InsertionSort
     * - BubbleSort
     */
    public static int[] vetorInvertido(int n) {

        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = n - i;
        }

        return v;
    }
}