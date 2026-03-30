package br.upe.analisealgoritmos.complexidade;

/*
 * CLASSE: AnaliseExemplos
 *
 * Objetivo:
 * Demonstrar análise de algoritmos usando modelagem matemática
 * e notação Big-O.
 */

public class AnaliseExemplos {

    /*
     * EXEMPLO 1: Complexidade O(1)
     *
     * Acesso direto a um elemento.
     */
    public static int constante(int[] vetor) {

        return vetor[0];

        /*
         * MODELAGEM:
         * T(n) = c
         *
         * COMPLEXIDADE:
         * O(1)
         */
    }

    /*
     * EXEMPLO 2: Complexidade O(n)
     *
     * Percorre todo o vetor.
     */
    public static void linear(int n) {

        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }

        /*
         * MODELAGEM:
         * T(n) = c * n
         *
         * COMPLEXIDADE:
         * O(n)
         */
    }

    /*
     * EXEMPLO 3: Complexidade O(n²)
     *
     * Dois loops aninhados.
     */
    public static void quadratica(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i + " " + j);
            }
        }

        /*
         * MODELAGEM:
         * T(n) = n * n = n²
         *
         * COMPLEXIDADE:
         * O(n²)
         */
    }

    /*
     * EXEMPLO 4: Complexidade O(log n)
     *
     * Divide o problema pela metade.
     */
    public static void logaritmica(int n) {

        while (n > 1) {
            n = n / 2;
        }

        /*
         * MODELAGEM:
         * n → n/2 → n/4 → ...
         *
         * T(n) = log n
         *
         * COMPLEXIDADE:
         * O(log n)
         */
    }

    /*
     * EXEMPLO 5: O(n log n)
     *
     * Loop + divisão.
     */
    public static void nLogN(int n) {

        for (int i = 0; i < n; i++) {

            int j = n;

            while (j > 1) {
                j = j / 2;
            }
        }

        /*
         * MODELAGEM:
         * T(n) = n * log n
         *
         * COMPLEXIDADE:
         * O(n log n)
         */
    }

    /*
     * EXEMPLO 6: Soma de complexidades
     */
    public static void soma(int n) {

        for (int i = 0; i < n; i++) {}

        for (int j = 0; j < n; j++) {}

        /*
         * MODELAGEM:
         * T(n) = n + n = 2n
         *
         * SIMPLIFICAÇÃO:
         * O(n)
         */
    }

    /*
     * EXEMPLO 7: Dominância
     */
    public static void dominante(int n) {

        for (int i = 0; i < n; i++) {}

        for (int i = 0; i < n * n; i++) {}

        /*
         * MODELAGEM:
         * T(n) = n + n²
         *
         * DOMINANTE:
         * n²
         *
         * COMPLEXIDADE:
         * O(n²)
         */
    }
    public static void main(String[] args) {

        int n = 10;

        linear(n);
        quadratica(n);
        logaritmica(n);
        nLogN(n);

        System.out.println("Análise concluída.");
    }
}