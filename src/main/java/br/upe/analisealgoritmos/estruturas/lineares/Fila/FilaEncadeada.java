package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: FilaEncadeada
 *
 * Implementação de uma fila usando lista encadeada simples.
 *
 * Princípio: FIFO (First In, First Out)
 * → O primeiro elemento inserido é o primeiro a sair
 */

public class FilaEncadeada {

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

    private Node inicio; // head (primeiro elemento)
    private Node fim;    // tail (último elemento)
    private int tamanho;

    /*
     * CONSTRUTOR
     *
     * Inicializa fila vazia.
     *
     * Complexidade: O(1)
     */
    public FilaEncadeada() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    /*
     * MÉTODO: enqueue
     *
     * Insere elemento no final da fila.
     *
     * Estratégia:
     * - Cria novo nó
     * - Liga o antigo "fim" ao novo nó
     * - Atualiza o "fim"
     *
     * Complexidade: O(1)
     */
    public void enqueue(int valor) {

        Node novo = new Node(valor);

        if (isEmpty()) {
            // fila vazia: início e fim apontam para o novo nó
            inicio = novo;
            fim = novo;
        } else {
            // liga o último nó ao novo
            fim.next = novo;
            fim = novo;
        }

        tamanho++;
    }

    /*
     * MÉTODO: dequeue
     *
     * Remove e retorna o primeiro elemento.
     *
     * Estratégia:
     * - Guarda valor do início
     * - Move início para o próximo nó
     *
     * Complexidade: O(1)
     */
    public int dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("Fila vazia");
        }

        int valor = inicio.valor;

        // move o início
        inicio = inicio.next;

        // se a fila ficou vazia, ajusta o fim
        if (inicio == null) {
            fim = null;
        }

        tamanho--;

        return valor;
    }

    /*
     * MÉTODO: peek
     *
     * Retorna o primeiro elemento sem remover.
     *
     * Complexidade: O(1)
     */
    public int peek() {

        if (isEmpty()) {
            throw new RuntimeException("Fila vazia");
        }

        return inicio.valor;
    }

    /*
     * MÉTODO: isEmpty
     *
     * Complexidade: O(1)
     */
    public boolean isEmpty() {
        return inicio == null;
    }

    /*
     * MÉTODO: size
     *
     * Complexidade: O(1)
     */
    public int size() {
        return tamanho;
    }

    /*
     * MÉTODO: imprimir (didático)
     *
     * Percorre a fila do início ao fim.
     *
     * Complexidade: O(n)
     */
    public void imprimir() {

        Node atual = inicio;

        System.out.print("Inicio -> ");

        while (atual != null) {
            System.out.print(atual.valor + " -> ");
            atual = atual.next;
        }

        System.out.println("null");
    }
}