package br.upe.analisealgoritmos.listas.lista03;

/*
 * ============================================================
 * QUESTÃO 4 — INTERSEÇÃO DE ARRAYS ORDENADOS
 * ============================================================
 *
 * OBJETIVO:
 * - Encontrar elementos em comum entre dois arrays
 *
 * ESTRATÉGIA:
 * - Dois ponteiros (i, j)
 * - Avança sempre → nunca volta
 *
 * COMPLEXIDADE:
 * - Θ(N) → percorre cada vetor uma única vez
 *
 * COMPARAÇÃO:
 * - Ingênuo → Θ(N²)
 * - Otimizado → Θ(N)
 *
 * OBS:
 * - NÃO mede tempo aqui
 * - Usa variável para evitar otimização da JVM
 *
 * ============================================================
 */

public class Q4_Intersecao {

    public static long executar(int n) {

        int[] a = new int[n];
        int[] b = new int[n];

        /*
         * Preenche arrays ordenados
         */
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = i;
        }

        int i = 0, j = 0;

        long contador = 0; // evita otimização

        /*
         * ============================================================
         * ALGORITMO DE INTERSEÇÃO (DOIS PONTEIROS)
         * ============================================================
         */
        while (i < n && j < n) {

            contador++;

            if (a[i] == b[j]) {
                i++;
                j++;
            }
            else if (a[i] < b[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        return contador;
    }
}