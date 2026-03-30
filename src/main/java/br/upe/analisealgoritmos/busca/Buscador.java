package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * INTERFACE: Buscador
 * ============================================================
 *
 * OBJETIVO:
 * Definir contrato para algoritmos de busca.
 *
 * ============================================================
 */

public interface Buscador {

    /*
     * ============================================================
     * MÉTODO DE BUSCA
     * ============================================================
     */
    int buscar(int[] vetor, int alvo);
}