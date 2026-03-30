package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: Deque
 *
 * Implementação de uma fila dupla (Double-Ended Queue)
 * usando lista duplamente encadeada.
 *
 * Permite inserção e remoção em ambas as extremidades.
 *
 * Operações principais:
 * - addFirst()
 * - addLast()
 * - removeFirst()
 * - removeLast()
 */

public class Deque {

    /*
     * Classe interna Node
     */
    private class Node {
        int valor;
        Node next;
        Node prev;

        Node(int valor) {
            this.valor = valor;
        }
    }

    private Node head; // início
    private Node tail; // fim
    private int tamanho;

    /*
     * CONSTRUTOR
     *
     * Complexidade: O(1)
     */
    public Deque() {
        head = null;
        tail = null;
        tamanho = 0;
    }

    /*
     * MÉTODO: addFirst
     *
     * Insere no início.
     *
     * Complexidade: O(1)
     */
    public void addFirst(int valor) {

        Node novo = new Node(valor);

        if (isEmpty()) {
            head = tail = novo;
        } else {
            novo.next = head;
            head.prev = novo;
            head = novo;
        }

        tamanho++;
    }

    /*
     * MÉTODO: addLast
     *
     * Insere no final.
     *
     * Complexidade: O(1)
     */
    public void addLast(int valor) {

        Node novo = new Node(valor);

        if (isEmpty()) {
            head = tail = novo;
        } else {
            tail.next = novo;
            novo.prev = tail;
            tail = novo;
        }

        tamanho++;
    }

    /*
     * MÉTODO: removeFirst
     *
     * Remove do início.
     *
     * Complexidade: O(1)
     */
    public int removeFirst() {

        if (isEmpty()) {
            throw new RuntimeException("Deque vazio");
        }

        int valor = head.valor;

        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        tamanho--;

        return valor;
    }

    /*
     * MÉTODO: removeLast
     *
     * Remove do final.
     *
     * Complexidade: O(1)
     */
    public int removeLast() {

        if (isEmpty()) {
            throw new RuntimeException("Deque vazio");
        }

        int valor = tail.valor;

        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        tamanho--;

        return valor;
    }

    /*
     * MÉTODO: peekFirst
     *
     * Consulta o primeiro elemento.
     *
     * Complexidade: O(1)
     */
    public int peekFirst() {

        if (isEmpty()) {
            throw new RuntimeException("Deque vazio");
        }

        return head.valor;
    }

    /*
     * MÉTODO: peekLast
     *
     * Consulta o último elemento.
     *
     * Complexidade: O(1)
     */
    public int peekLast() {

        if (isEmpty()) {
            throw new RuntimeException("Deque vazio");
        }

        return tail.valor;
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

    /*
     * MÉTODO: imprimir
     *
     * Complexidade: O(n)
     */
    public void imprimir() {

        Node atual = head;

        System.out.print("Deque: ");

        while (atual != null) {
            System.out.print(atual.valor + " <-> ");
            atual = atual.next;
        }

        System.out.println("null");
    }
}