package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * CLASSE: BuscaBinaria
 * ============================================================
 *
 * OBJETIVO:
 * Implementar busca binária.
 *
 * OBS:
 * Requer vetor ordenado.
 *
 * COMPLEXIDADE:
 * O(log n)
 *
 * ============================================================
 */

public class BuscaBinaria implements Buscador {

    /*
     * ============================================================
     * MÉTODO DE BUSCA
     * ============================================================
     */
    @Override
    public int buscar(int[] vetor, int alvo) {

        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {

            int meio = (inicio + fim) / 2;

            if (vetor[meio] == alvo) {
                return meio;
            }

            if (vetor[meio] < alvo) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return -1;
    }
}