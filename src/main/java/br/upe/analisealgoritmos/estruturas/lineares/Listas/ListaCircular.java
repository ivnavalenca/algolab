package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: ListaCircular
 *
 * Implementação de uma lista encadeada circular.
 *
 * Característica principal:
 * - O último nó aponta para o primeiro
 * - Não existe null no final
 */

public class ListaCircular {

    /*
     * Classe interna Node
     */
    private class Node {
        int valor;
        Node next;

        Node(int valor) {
            this.valor = valor;
        }
    }

    private Node head; // início da lista
    private Node tail; // último nó
    private int tamanho;

    /*
     * CONSTRUTOR
     *
     * Complexidade: O(1)
     */
    public ListaCircular() {
        head = null;
        tail = null;
        tamanho = 0;
    }

    /*
     * MÉTODO: adicionar
     *
     * Insere no final da lista.
     *
     * Complexidade: O(1)
     */
    public void adicionar(int valor) {

        Node novo = new Node(valor);

        if (isEmpty()) {
            head = tail = novo;
            novo.next = novo; // aponta para si mesmo
        } else {
            novo.next = head;
            tail.next = novo;
            tail = novo;
        }

        tamanho++;
    }

    /*
     * MÉTODO: removerInicio
     *
     * Complexidade: O(1)
     */
    public int removerInicio() {

        if (isEmpty()) {
            throw new RuntimeException("Lista vazia");
        }

        int valor = head.valor;

        if (head == tail) {
            // apenas um elemento
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }

        tamanho--;

        return valor;
    }

    /*
     * MÉTODO: buscar
     *
     * Complexidade: O(n)
     */
    public boolean buscar(int valor) {

        if (isEmpty()) return false;

        Node atual = head;

        do {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.next;
        } while (atual != head);

        return false;
    }

    /*
     * MÉTODO: imprimir (didático)
     *
     * Complexidade: O(n)
     */
    public void imprimir() {

        if (isEmpty()) {
            System.out.println("Lista vazia");
            return;
        }

        Node atual = head;

        System.out.print("Lista Circular: ");

        do {
            System.out.print(atual.valor + " -> ");
            atual = atual.next;
        } while (atual != head);

        System.out.println("(volta ao início)");
    }

    /*
     * MÉTODOS AUXILIARES
     */
    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return tamanho;
    }
}