package br.upe.analisealgoritmos.utils;

import java.util.Random;

/*
 * ============================================================
 * GERADOR DE VETORES (COMPATÍVEL + AVANÇADO)
 * ============================================================
 */

public class GeradorVetor {

    private static final Random random = new Random();

    /*
     * NOVOS MÉTODOS
     */

    public static int[] aleatorio(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = random.nextInt(n);
        }
        return v;
    }

    public static int[] ordenado(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        return v;
    }

    public static int[] reverso(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = n - i;
        }
        return v;
    }

    public static int[] quaseOrdenado(int n) {
        int[] v = ordenado(n);

        for (int i = 0; i < n / 10; i++) {
            int a = random.nextInt(n);
            int b = random.nextInt(n);

            int temp = v[a];
            v[a] = v[b];
            v[b] = temp;
        }

        return v;
    }

    /*
     * ============================================================
     * MÉTODOS ANTIGOS (COMPATIBILIDADE)
     * ============================================================
     */

    public static int[] gerarAleatorio(int n) {
        return aleatorio(n);
    }

    public static int[] gerarOrdenado(int n) {
        return ordenado(n);
    }

    public static int[] gerarInvertido(int n) {
        return reverso(n);
    }
}