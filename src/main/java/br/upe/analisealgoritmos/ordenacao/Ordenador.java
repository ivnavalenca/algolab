package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * INTERFACE ORDENADOR (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Define um contrato comum para todos os algoritmos de ordenação.
 *
 * Isso permite:
 * ✔ trocar algoritmos facilmente
 * ✔ reutilizar código nos experimentos
 * ✔ aplicar boas práticas de arquitetura
 *
 * ============================================================
 */

public interface Ordenador {

    /*
     * Método que todo algoritmo deve implementar
     */
    void ordenar(int[] vetor);

    /*
     * Nome do algoritmo (para gráficos e logs)
     */
    String getNome();
}