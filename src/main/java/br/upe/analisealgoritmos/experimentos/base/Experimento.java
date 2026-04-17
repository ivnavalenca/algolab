package br.upe.analisealgoritmos.experimentos.base;

/*
 * ============================================================
 * INTERFACE: Experimento
 * ============================================================
 *
 * OBJETIVO:
 * Padronizar todos os experimentos do sistema.
 *
 * ============================================================
 */

import java.util.List;

public interface Experimento {

    /*
     * Nome do experimento
     */
    String getNome();

    /*
     * Executa experimento
     */
    List<String[]> executar();
}