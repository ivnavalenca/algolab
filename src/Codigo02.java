// Codigo02.java

public class Codigo02 {

    /*
     * QUESTÃO:
     * Dada uma matriz N x N e um valor (val),
     * quantas vezes a linha "hits++" será executada?
     */

    public static int busca(int[][] m, int N, int val) {

        int hits = 0;

        // Percorre cada linha da matriz
        for (int i = 0; i < N; i++) {

            // Percorre cada coluna da linha
            for (int j = 0; j < N; j++) {

                // Verifica se encontrou o valor
                if (m[i][j] == val) {

                    hits++;   // ← LINHA ANALISADA

                    /*
                     * IMPORTANTE:
                     * O "break" faz com que, ao encontrar o valor,
                     * o laço interno pare imediatamente.
                     *
                     * Ou seja:
                     * Cada linha só pode contribuir no máximo com +1.
                     */
                    break;
                }
            }
        }

        return hits;
    }

    public static void main(String[] args) {

        int[][] m = {
            {6, 3, 7, 6, 0},
            {7, 0, 7, 8, 0},
            {0, 0, 0, 8, 7},
            {10, 7, 7, 8, 0},
            {0, 9, 9, 7, 7}
        };

        int N = 5;
        int val = 7;

        int resultado = busca(m, N, val);

        System.out.println("Quantidade de execuções de hits++: " + resultado);

        /*
         * ✅ RESPOSTA:
         *
         * Precisamos contar quantas LINHAS possuem pelo menos um valor 7.
         *
         * Linha 0 → tem 7 → conta 1
         * Linha 1 → tem 7 → conta 1
         * Linha 2 → tem 7 → conta 1
         * Linha 3 → tem 7 → conta 1
         * Linha 4 → tem 7 → conta 1
         *
         * Total = 5
         *
         * 👉 Portanto, a linha "hits++" é executada 5 vezes.
         *
         * 💡 OBSERVAÇÃO IMPORTANTE:
         * Mesmo que a linha tenha vários 7,
         * o "break" garante que só conte UMA vez por linha.
         */
    }
}