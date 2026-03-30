package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * INTERFACE BUSCADOR (Strategy Pattern)
 * ============================================================
 *
 * OBJETIVO:
 * Definir um contrato comum para todos os algoritmos de busca.
 *
 * ============================================================
 * BENEFÍCIOS:
 *
 * ✔ Padronização (igual aos ordenadores)
 * ✔ Permite troca dinâmica de algoritmo
 * ✔ Facilita experimentos
 * ✔ Código mais limpo e escalável
 *
 * ============================================================
 */

public interface Buscador {

    /*
     * Método de busca
     *
     * @param vetor  vetor onde será feita a busca
     * @param chave  valor a ser procurado
     *
     * @return índice do elemento encontrado
     *         ou -1 se não encontrado
     */
    int buscar(int[] vetor, int chave);

    /*
     * Nome do algoritmo (para logs e gráficos)
     */
    String getNome();
}