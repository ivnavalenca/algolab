package br.upe.analisealgoritmos.ordenacao;

import java.util.Random;

/*
 * ============================================================
 * QUICK SORT (VERSÃO PROFISSIONAL - PIVÔ ALEATÓRIO)
 * ============================================================
 *
 * IDEIA:
 * Algoritmo baseado em "Dividir para Conquistar".
 *
 * Passos:
 * 1. Escolhe um pivô (agora aleatório)
 * 2. Reorganiza o vetor:
 *    - menores que o pivô → esquerda
 *    - maiores que o pivô → direita
 * 3. Aplica recursivamente
 *
 * ============================================================
 * POR QUE PIVÔ ALEATÓRIO?
 *
 * Evita o pior caso frequente quando o vetor já está ordenado.
 *
 * Antes:
 *   pivô fixo → pode gerar O(n²)
 *
 * Agora:
 *   pivô aleatório → tende a O(n log n)
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(n log n)
 * Caso médio: O(n log n)
 * Pior caso: O(n²) (raro com pivô aleatório)
 *
 * ============================================================
 * CARACTERÍSTICAS:
 *
 * ✔ Muito rápido na prática
 * ✔ In-place (não usa memória extra relevante)
 * ✔ Boa escolha para aplicações reais
 *
 * ============================================================
 */

public class QuickSort implements Ordenador {

    /*
     * Gerador de números aleatórios
     */
    private final Random random = new Random();

    /*
     * Método público (entrada do algoritmo)
     */
    @Override
    public void ordenar(int[] vetor) {
        quickSort(vetor, 0, vetor.length - 1);
    }

    /*
     * Função recursiva principal
     */
    private void quickSort(int[] vetor, int inicio, int fim) {

        /*
         * Caso base: se o intervalo é válido
         */
        if (inicio < fim) {

            /*
             * Particiona o vetor e encontra posição do pivô
             */
            int pivoIndex = particionar(vetor, inicio, fim);

            /*
             * Ordena parte esquerda
             */
            quickSort(vetor, inicio, pivoIndex - 1);

            /*
             * Ordena parte direita
             */
            quickSort(vetor, pivoIndex + 1, fim);
        }
    }

    /*
     * Função de particionamento
     *
     * Responsável por:
     * - colocar o pivô na posição correta
     * - reorganizar elementos ao redor dele
     */
    private int particionar(int[] vetor, int inicio, int fim) {

        /*
         * Escolhe pivô aleatório
         */
        int pivotIndex = inicio + random.nextInt(fim - inicio + 1);

        /*
         * Troca pivô com último elemento
         */
        int temp = vetor[pivotIndex];
        vetor[pivotIndex] = vetor[fim];
        vetor[fim] = temp;

        int pivo = vetor[fim];

        /*
         * i separa menores e maiores
         */
        int i = inicio - 1;

        /*
         * Percorre o vetor
         */
        for (int j = inicio; j < fim; j++) {

            /*
             * Se elemento menor que pivô → vai para esquerda
             */
            if (vetor[j] < pivo) {
                i++;

                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }

        /*
         * Coloca pivô na posição correta
         */
        temp = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temp;

        return i + 1;
    }

    /*
     * Nome do algoritmo (para logs e gráficos)
     */
    @Override
    public String getNome() {
        return "QuickSort(RandomPivot)";
    }
}