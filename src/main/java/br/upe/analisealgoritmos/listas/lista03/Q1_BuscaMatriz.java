package br.upe.analisealgoritmos.listas.lista03;

/*
 * ============================================================
 * QUESTÃO 1 — BUSCA EM MATRIZ (N x N)
 * ============================================================
 *
 * OBJETIVO:
 * - Comparar melhor e pior caso
 *
 * COMPLEXIDADE:
 * - Melhor caso → Θ(1)
 * - Pior caso → Θ(N²)
 *
 * OBS:
 * - NÃO mede tempo aqui (benchmark externo)
 *
 * ============================================================
 */

public class Q1_BuscaMatriz {

    /*
     * ============================================================
     * PIOR CASO → percorre toda matriz
     * ============================================================
     */
    public static long executarPiorCaso(int n) {

        int[][] matriz = new int[n][n];
        int alvo = -1; // não existe

        long contador = 0;

        boolean achou = false;

        for (int i = 0; i < n && !achou; i++) {
            for (int j = 0; j < n && !achou; j++) {

                contador++; // evita otimização

                if (matriz[i][j] == alvo) {
                    achou = true;
                }
            }
        }

        return contador;
    }

    /*
     * ============================================================
     * MELHOR CASO → encontra imediatamente
     * ============================================================
     */
    public static long executarMelhorCaso(int n) {

        int[][] matriz = new int[n][n];
        matriz[0][0] = 1;

        int alvo = 1;

        long contador = 0;

        boolean achou = false;

        for (int i = 0; i < n && !achou; i++) {
            for (int j = 0; j < n && !achou; j++) {

                contador++;

                if (matriz[i][j] == alvo) {
                    achou = true;
                }
            }
        }

        return contador;
    }
}