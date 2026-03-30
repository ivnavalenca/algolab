package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * INSERTION SORT (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Insere cada elemento na posição correta dentro da parte já ordenada.
 *
 * Semelhante a organizar cartas na mão.
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(n)   → vetor já ordenado
 * Caso médio: O(n²)
 * Pior caso: O(n²)
 *
 * ============================================================
 * CARACTERÍSTICAS:
 *
 * ✔ Adaptativo (melhora com entrada ordenada)
 * ✔ Bom para pequenos conjuntos de dados
 *
 * ============================================================
 */

public class InsertionSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {

        for (int i = 1; i < vetor.length; i++) {

            int chave = vetor[i];
            int j = i - 1;

            /*
             * Move os elementos maiores que a chave
             */
            while (j >= 0 && vetor[j] > chave) {
                vetor[j + 1] = vetor[j];
                j--;
            }

            /*
             * Insere a chave na posição correta
             */
            vetor[j + 1] = chave;
        }
    }

    @Override
    public String getNome() {
        return "InsertionSort";
    }
}