package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * BUSCA BINÁRIA (Strategy Pattern)
 * ============================================================
 *
 * IDEIA:
 * Divide o vetor ao meio a cada iteração.
 *
 * Compara com o elemento central e descarta metade dos dados.
 *
 * ============================================================
 * PRÉ-REQUISITO:
 *
 * ⚠ O vetor DEVE estar ordenado!
 *
 * ============================================================
 * COMPLEXIDADE:
 *
 * Melhor caso: O(1)
 *  → elemento está no meio
 *
 * Caso médio: O(log n)
 *
 * Pior caso: O(log n)
 *
 * ============================================================
 * CARACTERÍSTICAS:
 *
 * ✔ Muito eficiente
 * ✔ Redução pela metade a cada passo
 * ❌ Requer ordenação prévia
 *
 * ============================================================
 */

public class BuscaBinaria implements Buscador {

    /*
     * Método de busca binária
     */
    @Override
    public int buscar(int[] vetor, int chave) {

        int inicio = 0;
        int fim = vetor.length - 1;

        /*
         * Enquanto houver intervalo válido
         */
        while (inicio <= fim) {

            /*
             * Calcula posição central
             */
            int meio = (inicio + fim) / 2;

            /*
             * Verifica se encontrou
             */
            if (vetor[meio] == chave) {
                return meio;
            }

            /*
             * Busca na metade esquerda
             */
            if (vetor[meio] > chave) {
                fim = meio - 1;
            }
            /*
             * Busca na metade direita
             */
            else {
                inicio = meio + 1;
            }
        }

        /*
         * Elemento não encontrado
         */
        return -1;
    }

    /*
     * Nome do algoritmo
     */
    @Override
    public String getNome() {
        return "BuscaBinaria";
    }
}