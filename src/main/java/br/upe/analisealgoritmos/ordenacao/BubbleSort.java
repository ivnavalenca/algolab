package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * BUBBLE SORT (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Compara elementos adjacentes e troca se estiverem fora de ordem.
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(n)   → com otimização (sem trocas)
 * Caso médio: O(n²)
 * Pior caso: O(n²)
 *
 * ============================================================
 * OBSERVAÇÃO:
 * Implementa a interface Ordenador, permitindo uso polimórfico.
 * ============================================================
 */

public class BubbleSort implements Ordenador {

    @Override
    public void ordenar(int[] vetor) {

        int n = vetor.length;
        boolean houveTroca;

        for (int i = 0; i < n - 1; i++) {

            houveTroca = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (vetor[j] > vetor[j + 1]) {

                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;

                    houveTroca = true;
                }
            }

            /*
             * Otimização: se não houve troca, já está ordenado
             */
            if (!houveTroca) {
                break;
            }
        }
    }

    @Override
    public String getNome() {
        return "BubbleSort";
    }
}