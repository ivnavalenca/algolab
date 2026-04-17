package br.upe.analisealgoritmos.utils.dados;

/*
 * ============================================================
 * CLASSE: GeradorDados
 * ============================================================
 *
 * OBJETIVO:
 * Centralizar a geração de dados de entrada para experimentos.
 *
 * TIPOS SUPORTADOS:
 * ✔ Vetor aleatório
 * ✔ Vetor ordenado
 * ✔ Vetor reverso (pior caso)
 *
 * GARANTIAS:
 * ✔ Reprodutibilidade (seed fixa)
 * ✔ Consistência entre experimentos
 *
 * ============================================================
 */

import java.util.Random;

public class GeradorDados {

    /*
     * ============================================================
     * CONFIGURAÇÕES
     * ============================================================
     */
    private static final Random RANDOM = new Random(42);
    private static final int DEFAULT_MAX = 100000;

    /*
     * ============================================================
     * ENUM DE CENÁRIOS
     * ============================================================
     */
    public enum TipoEntrada {
        ALEATORIO,
        ORDENADO,
        REVERSO
    }

    /*
     * ============================================================
     * MÉTODO GENÉRICO
     * ============================================================
     */
    public static int[] gerar(int n, TipoEntrada tipo) {

        validarTamanho(n);

        switch (tipo) {
            case ALEATORIO:
                return gerarAleatorio(n);
            case ORDENADO:
                return gerarOrdenado(n);
            case REVERSO:
                return gerarReverso(n);
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }

    /*
     * ============================================================
     * VETOR ALEATÓRIO
     * ============================================================
     */
    public static int[] gerarAleatorio(int n) {
        return gerarAleatorio(n, DEFAULT_MAX);
    }

    public static int[] gerarAleatorio(int n, int max) {

        validarTamanho(n);

        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = RANDOM.nextInt(max);
        }

        return vetor;
    }

    /*
     * ============================================================
     * VETOR ORDENADO
     * ============================================================
     */
    public static int[] gerarOrdenado(int n) {

        validarTamanho(n);

        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = i;
        }

        return vetor;
    }

    /*
     * ============================================================
     * VETOR REVERSO
     * ============================================================
     */
    public static int[] gerarReverso(int n) {

        validarTamanho(n);

        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = n - i;
        }

        return vetor;
    }

    /*
     * ============================================================
     * VALIDAÇÃO
     * ============================================================
     */
    private static void validarTamanho(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("Tamanho deve ser maior que zero");
        }
    }
}