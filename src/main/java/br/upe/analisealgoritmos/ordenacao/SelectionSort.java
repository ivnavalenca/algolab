package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * SELECTION SORT (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Seleciona o menor elemento do vetor e coloca na posição correta.
 *
 * A cada iteração:
 * - encontra o menor elemento
 * - troca com a posição atual
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(n²)
 * Caso médio: O(n²)
 * Pior caso: O(n²)
 *
 * 👉 IMPORTANTE:
 * Não é adaptativo → não melhora com vetor ordenado
 *
 * ============================================================
 * CARACTERÍSTICAS:
 *
 * ✔ Simples
 * ✔ Poucas trocas
 * ❌ Sempre percorre todo o vetor
 *
 * ============================================================
 */

public class SelectionSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {

        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {

            int indiceMenor = i;

            /*
             * Busca o menor elemento
             */
            for (int j = i + 1; j < n; j++) {

                if (vetor[j] < vetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }

            /*
             * Troca com a posição atual
             */
            int temp = vetor[i];
            vetor[i] = vetor[indiceMenor];
            vetor[indiceMenor] = temp;
        }
    }

    @Override
    public String getNome() {
        return "SelectionSort";
    }
}