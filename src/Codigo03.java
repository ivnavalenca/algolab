// Codigo03.java

public class Codigo03 {

    public static void main(String[] args) {

        /*
         * QUESTÃO:
         * Uma lista começa com capacidade 5.
         * Ao encher, ela dobra de tamanho.
         *
         * Perguntas:
         * a) Quantas vezes ocorre redimensionamento?
         * b) Quantas cópias são feitas no total?
         */

        int capacidade = 5;     // capacidade inicial
        int tamanho = 0;        // quantidade de elementos inseridos
        int[] vetor = new int[capacidade];

        int redimensionamentos = 0;
        int copias = 0;

        // Inserindo elementos de 1 até 33
        for (int i = 1; i < 34; i++) {

            /*
             * Se o vetor estiver cheio → precisa redimensionar
             */
            if (tamanho == capacidade) {

                int novaCapacidade = capacidade * 2;
                int[] novoVetor = new int[novaCapacidade];

                /*
                 * Copia todos os elementos do vetor antigo
                 * para o novo vetor maior
                 */
                for (int j = 0; j < capacidade; j++) {
                    novoVetor[j] = vetor[j];
                    copias++; // conta cada cópia realizada
                }

                vetor = novoVetor;
                capacidade = novaCapacidade;
                redimensionamentos++;

                /*
                 * Evolução das capacidades:
                 * 5 → 10 → 20 → 40
                 */
            }

            // Insere o elemento
            vetor[tamanho] = i;
            tamanho++;
        }

        System.out.println("Redimensionamentos: " + redimensionamentos);
        System.out.println("Total de cópias: " + copias);

        /*
         * ✅ RESPOSTA:
         *
         * Inserimos 33 elementos (de 1 até 33)
         *
         * 📌 REDIMENSIONAMENTOS:
         *
         * Capacidade inicial = 5
         *
         * Quando enche:
         * - 6º elemento → 5 → 10
         * - 11º elemento → 10 → 20
         * - 21º elemento → 20 → 40
         *
         * 👉 Total = 3 redimensionamentos
         *
         *
         * 📌 CÓPIAS:
         *
         * Cada redimensionamento copia TODOS os elementos:
         *
         * - 5 elementos copiados (5 → 10)
         * - 10 elementos copiados (10 → 20)
         * - 20 elementos copiados (20 → 40)
         *
         * Total:
         * 5 + 10 + 20 = 35 cópias
         *
         * 👉 Total = 35 cópias
         *
         *
         * 💡 CONCEITO IMPORTANTE:
         *
         * Apesar das cópias, o custo médio de inserção é O(1)
         * (complexidade amortizada).
         */
    }
}