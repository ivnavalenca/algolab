/*
 * CLASSE: ExemploArray
 *
 * Representa o uso básico de um vetor (array).
 *
 * Estrutura de acesso direto:
 * - Elementos armazenados em memória contígua
 * - Acesso por índice
 */

public class ExemploArray {

    public static void main(String[] args) {

        int[] a = {10, 20, 30, 40, 50};

        /*
         * ACESSO DIRETO
         *
         * Complexidade: O(1)
         */
        int valor = a[2]; // acesso direto

        /*
         * BUSCA SEQUENCIAL
         *
         * Complexidade: O(n)
         */
        int buscado = 40;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == buscado) {
                System.out.println("Encontrado na posição: " + i);
            }
        }

        /*
         * INSERÇÃO (simulada)
         *
         * Complexidade: O(n)
         * (precisa deslocar elementos)
         */

        /*
         * REMOÇÃO (simulada)
         *
         * Complexidade: O(n)
         */
    }
}