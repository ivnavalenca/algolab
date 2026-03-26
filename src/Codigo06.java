// Codigo06.java

// Classe que representa um nó da lista encadeada
class Node {
    int valor;
    Node next;

    Node(int valor) {
        this.valor = valor;
        this.next = null;
    }
}

public class Codigo06 {

    public static void main(String[] args) {

        /*
         * QUESTÃO:
         * Dado um nó x (que NÃO é o último),
         * qual o efeito do código:
         *
         * x.next = x.next.next;
         */

        // Criando manualmente a lista:
        // 1 → 2 → 3

        Node x = new Node(1);
        x.next = new Node(2);
        x.next.next = new Node(3);

        /*
         * Estrutura antes:
         *
         * x → [1] → [2] → [3] → null
         */

        // Linha analisada:
        x.next = x.next.next;

        /*
         * O que acontece aqui?
         *
         * x.next (que era o nó 2)
         * passa a apontar para o próximo do próximo (nó 3)
         *
         * Ou seja:
         * o nó com valor 2 é "pulado"
         */

        /*
         * Estrutura depois:
         *
         * x → [1] → [3] → null
         *
         * O nó [2] foi removido da lista!
         */

        // Percorrendo a lista para mostrar o resultado
        Node atual = x;

        System.out.print("Lista após operação: ");
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.next;
        }

        /*
         * RESPOSTA:
         *
         * O código remove o nó imediatamente após x.
         *
         * É uma remoção em lista encadeada
         *
         *
         * IMPORTANTE:
         *
         * Isso só funciona se:
         * - x NÃO for o último nó
         * - x.next NÃO for null
         *
         *
         * COMPLEXIDADE:
         *
         * Tempo: O(1)
         * (não precisa percorrer a lista)
         *
         *
         * CONCEITO IMPORTANTE:
         *
         * Em listas encadeadas,
         * remover um elemento pode ser muito eficiente,
         * desde que você tenha a referência correta.
         */
    }
}