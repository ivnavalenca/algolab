package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * MERGE SORT (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Divide o vetor em partes menores, ordena cada parte
 * e depois combina (merge) os resultados.
 *
 * Estratégia: DIVIDIR PARA CONQUISTAR
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(n log n)
 * Caso médio: O(n log n)
 * Pior caso: O(n log n)
 *
 * 👉 Sempre mantém a mesma complexidade
 *
 * ============================================================
 * CARACTERÍSTICAS:
 *
 * ✔ Estável
 * ✔ Previsível
 * ❌ Usa memória extra
 *
 * ============================================================
 */

public class MergeSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {
        mergeSort(vetor, 0, vetor.length - 1);
    }

    /*
     * Função recursiva
     */
    private void mergeSort(int[] vetor, int inicio, int fim) {

        if (inicio >= fim) return;

        int meio = (inicio + fim) / 2;

        /*
         * Divide o problema
         */
        mergeSort(vetor, inicio, meio);
        mergeSort(vetor, meio + 1, fim);

        /*
         * Combina as partes
         */
        merge(vetor, inicio, meio, fim);
    }

    /*
     * Função de intercalação
     */
    private void merge(int[] vetor, int inicio, int meio, int fim) {

        int[] esquerda = new int[meio - inicio + 1];
        int[] direita = new int[fim - meio];

        /*
         * Copia dados
         */
        for (int i = 0; i < esquerda.length; i++) {
            esquerda[i] = vetor[inicio + i];
        }

        for (int i = 0; i < direita.length; i++) {
            direita[i] = vetor[meio + 1 + i];
        }

        int i = 0, j = 0, k = inicio;

        /*
         * Intercala mantendo ordenação
         */
        while (i < esquerda.length && j < direita.length) {

            if (esquerda[i] <= direita[j]) {
                vetor[k++] = esquerda[i++];
            } else {
                vetor[k++] = direita[j++];
            }
        }

        /*
         * Copia o restante
         */
        while (i < esquerda.length) {
            vetor[k++] = esquerda[i++];
        }

        while (j < direita.length) {
            vetor[k++] = direita[j++];
        }
    }

    @Override
    public String getNome() {
        return "MergeSort";
    }
}