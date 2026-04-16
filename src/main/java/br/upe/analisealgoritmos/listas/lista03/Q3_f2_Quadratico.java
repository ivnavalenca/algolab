package br.upe.analisealgoritmos.listas.lista03;

/*
 * ============================================================
 * QUESTÃO 3 — COMPLEXIDADE QUADRÁTICA
 * ============================================================
 *
 * IDEIA:
 * - f1 → Θ(N)
 * - f2 chama f1 dentro de um loop → Θ(N²)
 *
 * OBJETIVO:
 * - Demonstrar crescimento quadrático na prática
 *
 * OBS:
 * - NÃO mede tempo aqui
 * - Retorna valor para evitar otimização da JVM
 *
 * ============================================================
 */

public class Q3_f2_Quadratico {

    /*
     * ============================================================
     * f1 → encontra o maior elemento (Θ(N))
     * ============================================================
     */
    private static int f1(int[] v) {

        int max = v[0];

        for (int i = 1; i < v.length; i++) {
            if (v[i] > max) {
                max = v[i];
            }
        }

        return max;
    }

    /*
     * ============================================================
     * f2 → chama f1 N vezes → Θ(N²)
     * ============================================================
     */
    public static long executar(int n) {

        int[] v = new int[n];

        long resultado = 0; // evita otimização

        for (int i = 0; i < n; i++) {

            int y = f1(v); // Θ(N)

            resultado += y; // uso do resultado
            v[i] = y;
        }

        return resultado;
    }
}