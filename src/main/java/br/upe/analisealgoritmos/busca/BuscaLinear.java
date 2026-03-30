package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * CLASSE: BuscaLinear
 * ============================================================
 *
 * OBJETIVO:
 * Implementar busca linear.
 *
 * COMPLEXIDADE:
 * O(n)
 *
 * ============================================================
 */

public class BuscaLinear implements Buscador {

    /*
     * ============================================================
     * MÉTODO DE BUSCA
     * ============================================================
     */
    @Override
    public int buscar(int[] vetor, int alvo) {

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == alvo) {
                return i;
            }
        }

        return -1;
    }
}