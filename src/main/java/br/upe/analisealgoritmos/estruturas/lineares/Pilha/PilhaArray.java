package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: PilhaArray
 *
 * Implementação de uma Pilha (Stack) usando vetor (array).
 *
 * Princípio: LIFO (Last In, First Out)
 * → O último elemento inserido é o primeiro a sair
 */

public class PilhaArray {

    private int[] elementos; // vetor que armazena os dados
    private int topo;        // índice do topo da pilha

    /*
     * CONSTRUTOR
     *
     * Inicializa a pilha com uma capacidade inicial.
     *
     * Complexidade: O(1)
     */
    public PilhaArray(int capacidade) {
        elementos = new int[capacidade];
        topo = -1; // pilha vazia
    }

    /*
     * MÉTODO: push
     *
     * Insere um elemento no topo da pilha.
     *
     * Complexidade:
     * - Caso comum: O(1)
     * - Com redimensionamento: O(n)
     * - Amortizada: O(1)
     */
    public void push(int valor) {

        // Se a pilha estiver cheia, redimensiona
        if (topo == elementos.length - 1) {
            redimensionar();
        }

        topo++;
        elementos[topo] = valor;
    }

    /*
     * MÉTODO: pop
     *
     * Remove e retorna o elemento do topo.
     *
     * Complexidade: O(1)
     */
    public int pop() {

        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }

        int valor = elementos[topo];
        topo--;

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

        return elementos[topo];
    }

    /*
     * MÉTODO: isEmpty
     *
     * Verifica se a pilha está vazia.
     *
     * Complexidade: O(1)
     */
    public boolean isEmpty() {
        return topo == -1;
    }

    /*
     * MÉTODO: size
     *
     * Retorna a quantidade de elementos.
     *
     * Complexidade: O(1)
     */
    public int size() {
        return topo + 1;
    }

    /*
     * MÉTODO: redimensionar
     *
     * Dobra o tamanho do vetor quando ele enche.
     *
     * Complexidade: O(n)
     * (precisa copiar todos os elementos)
     */
    private void redimensionar() {

        int[] novo = new int[elementos.length * 2];

        // copia elementos
        for (int i = 0; i < elementos.length; i++) {
            novo[i] = elementos[i];
        }

        elementos = novo;
    }
}