package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: ListaDuplamenteEncadeada
 *
 * Implementação de uma lista duplamente encadeada.
 *
 * Características:
 * - Cada nó aponta para o próximo (next)
 * - E também para o anterior (prev)
 * - Permite navegação nos dois sentidos
 */

public class ListaDuplamenteEncadeada {

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
    public ListaDuplamenteEncadeada() {
        head = null;
        tail = null;
        tamanho = 0;
    }

    /*
     * MÉTODO: adicionarInicio
     *
     * Complexidade: O(1)
     */
    public void adicionarInicio(int valor) {

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
     * MÉTODO: adicionarFim
     *
     * Complexidade: O(1)
     */
    public void adicionarFim(int valor) {

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
     * MÉTODO: removerInicio
     *
     * Complexidade: O(1)
     */
    public int removerInicio() {

        if (isEmpty()) {
            throw new RuntimeException("Lista vazia");
        }

        int valor = head.valor;

        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // lista ficou vazia
        }

        tamanho--;

        return valor;
    }

    /*
     * MÉTODO: removerFim
     *
     * Complexidade: O(1)
     */
    public int removerFim() {

        if (isEmpty()) {
            throw new RuntimeException("Lista vazia");
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
     * MÉTODO: removerPorValor
     *
     * Complexidade: O(n)
     */
    public void removerPorValor(int valor) {

        Node atual = head;

        while (atual != null) {

            if (atual.valor == valor) {

                // caso seja o primeiro
                if (atual == head) {
                    removerInicio();
                }
                // caso seja o último
                else if (atual == tail) {
                    removerFim();
                }
                // caso intermediário
                else {
                    atual.prev.next = atual.next;
                    atual.next.prev = atual.prev;
                    tamanho--;
                }

                return;
            }

            atual = atual.next;
        }
    }

    /*
     * MÉTODO: buscar
     *
     * Complexidade: O(n)
     */
    public boolean buscar(int valor) {

        Node atual = head;

        while (atual != null) {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.next;
        }

        return false;
    }

    /*
     * MÉTODO: imprimir (normal)
     *
     * Complexidade: O(n)
     */
    public void imprimir() {

        Node atual = head;

        System.out.print("Inicio -> ");

        while (atual != null) {
            System.out.print(atual.valor + " <-> ");
            atual = atual.next;
        }

        System.out.println("null");
    }

    /*
     * MÉTODO: imprimir reverso
     *
     * Complexidade: O(n)
     */
    public void imprimirReverso() {

        Node atual = tail;

        System.out.print("Fim -> ");

        while (atual != null) {
            System.out.print(atual.valor + " <-> ");
            atual = atual.prev;
        }

        System.out.println("null");
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