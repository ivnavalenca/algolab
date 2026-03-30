package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: PilhaEncadeada
 *
 * Implementação de uma pilha usando lista encadeada simples.
 *
 * Princípio: LIFO (Last In, First Out)
 * → O último elemento inserido é o primeiro a sair
 */

public class PilhaEncadeada {

    /*
     * Classe interna Node (nó da lista)
     *
     * Cada nó armazena:
     * - valor
     * - referência para o próximo nó
     */
    private class Node {
        int valor;
        Node next;

        Node(int valor) {
            this.valor = valor;
            this.next = null;
        }
    }

    private Node topo; // referência para o topo da pilha
    private int tamanho;

    /*
     * CONSTRUTOR
     *
     * Inicializa pilha vazia.
     *
     * Complexidade: O(1)
     */
    public PilhaEncadeada() {
        topo = null;
        tamanho = 0;
    }

    /*
     * MÉTODO: push
     *
     * Insere um elemento no topo da pilha.
     *
     * Estratégia:
     * - Cria um novo nó
     * - Aponta ele para o antigo topo
     * - Atualiza o topo
     *
     * Complexidade: O(1)
     */
    public void push(int valor) {

        Node novo = new Node(valor);

        // novo aponta para o antigo topo
        novo.next = topo;

        // atualiza topo
        topo = novo;

        tamanho++;
    }

    /*
     * MÉTODO: pop
     *
     * Remove e retorna o elemento do topo.
     *
     * Estratégia:
     * - Guarda o valor do topo
     * - Move o topo para o próximo nó
     *
     * Complexidade: O(1)
     */
    public int pop() {

        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }

        int valor = topo.valor;

        // move o topo
        topo = topo.next;

        tamanho--;

        return valor;
    }

    /*
     * MÉTODO: peek
     *
     * Retorna o topo sem remover.
     *
     * Complexidade: O(1)
     */
    public int peek() {

        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }

        return topo.valor;
    }

    /*
     * MÉTODO: isEmpty
     *
     * Verifica se a pilha está vazia.
     *
     * Complexidade: O(1)
     */
    public boolean isEmpty() {
        return topo == null;
    }

    /*
     * MÉTODO: size
     *
     * Retorna a quantidade de elementos.
     *
     * Complexidade: O(1)
     */
    public int size() {
        return tamanho;
    }

    /*
     * MÉTODO: imprimir (didático)
     *
     * Percorre a pilha do topo até o final.
     *
     * Complexidade: O(n)
     */
    public void imprimir() {

        Node atual = topo;

        System.out.print("Topo -> ");

        while (atual != null) {
            System.out.print(atual.valor + " -> ");
            atual = atual.next;
        }

        System.out.println("null");
    }
}