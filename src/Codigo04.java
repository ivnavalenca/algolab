// Codigo04.java

public class Codigo04 {

    public static void main(String[] args) {

        /*
         * QUESTÃO:
         * O que o código abaixo faz?
         * Ele é eficiente?
         */

        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};

        /*
         * Antes da troca:
         *
         * a → [1, 2, 3]
         * b → [4, 5, 6]
         */

        // Troca (swap) das referências
        int[] t = a; // t aponta para o mesmo vetor de a
        a = b;       // a passa a apontar para b
        b = t;       // b passa a apontar para o antigo a

        /*
         * Depois da troca:
         *
         * a → [4, 5, 6]
         * b → [1, 2, 3]
         *
         * IMPORTANTE:
         * Não houve cópia dos elementos!
         * Apenas trocamos as referências (endereços na memória).
         */

        System.out.print("Array a: ");
        for (int x : a) {
            System.out.print(x + " ");
        }

        System.out.print("\nArray b: ");
        for (int x : b) {
            System.out.print(x + " ");
        }

        /*
         * RESPOSTA:
         *
         * O código realiza a troca (swap) entre os vetores a e b.
         *
         * NÃO copia os elementos.
         * Apenas troca as referências.
         *
         * EFICIÊNCIA:
         *
         * Tempo: O(1) → constante
         * Espaço: O(1)
         *
         * Extremamente eficiente!
         *
         *
         * CONCEITO IMPORTANTE:
         *
         * Em Java, arrays são objetos.
         * Variáveis como "a" e "b" armazenam REFERÊNCIAS (endereços),
         * não os valores diretamente.
         */
    }
}