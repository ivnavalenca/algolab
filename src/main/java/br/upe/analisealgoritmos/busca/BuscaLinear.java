package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * BUSCA LINEAR (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Percorre o vetor do início ao fim procurando o elemento.
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(1)
 *  → elemento está na primeira posição
 *
 * Caso médio: O(n)
 *
 * Pior caso: O(n)
 *  → elemento está no final ou não existe
 *
 * ============================================================
 * CARACTERÍSTICAS:
 *
 * ✔ Simples
 * ✔ Funciona com vetor NÃO ordenado
 * ❌ Ineficiente para grandes volumes
 *
 * ============================================================
 */

public class BuscaLinear implements Buscador {

    /*
     * Método de busca
     */
    @Override
    public int buscar(int[] vetor, int chave) {

        /*
         * Percorre todo o vetor
         */
        for (int i = 0; i < vetor.length; i++) {

            /*
             * Compara com a chave
             */
            if (vetor[i] == chave) {

                /*
                 * Retorna índice encontrado
                 */
                return i;
            }
        }

        /*
         * Não encontrou
         */
        return -1;
    }

    /*
     * Nome do algoritmo
     */
    @Override
    public String getNome() {
        return "BuscaLinear";
    }
}