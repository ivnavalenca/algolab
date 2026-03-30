package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: FilaArrayCircular
 *
 * Implementação de uma fila usando array circular.
 *
 * Princípio: FIFO (First In, First Out)
 * → O primeiro elemento inserido é o primeiro a sair
 */

public class FilaArrayCircular {

    private int[] elementos;
    private int inicio; // índice do primeiro elemento
    private int fim;    // índice onde será inserido o próximo elemento
    private int tamanho;

    /*
     * CONSTRUTOR
     *
     * Complexidade: O(1)
     */
    public FilaArrayCircular(int capacidade) {
        elementos = new int[capacidade];
        inicio = 0;
        fim = 0;
        tamanho = 0;
    }

    /*
     * MÉTODO: enqueue
     *
     * Insere elemento no final da fila.
     *
     * Complexidade:
     * - Caso comum: O(1)
     * - Com redimensionamento: O(n)
     * - Amortizada: O(1)
     */
    public void enqueue(int valor) {

        // Se estiver cheia, redimensiona
        if (tamanho == elementos.length) {
            redimensionar();
        }

        elementos[fim] = valor;

        // Avança circularmente
        fim = (fim + 1) % elementos.length;

        tamanho++;
    }

    /*
     * MÉTODO: dequeue
     *
     * Remove o primeiro elemento da fila.
     *
     * Complexidade: O(1)
     */
    public int dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("Fila vazia");
        }

        int valor = elementos[inicio];

        // Avança circularmente
        inicio = (inicio + 1) % elementos.length;

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

        return elementos[inicio];
    }

    /*
     * MÉTODO: isEmpty
     *
     * Complexidade: O(1)
     */
    public boolean isEmpty() {
        return tamanho == 0;
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
     * MÉTODO: redimensionar
     *
     * Dobra a capacidade do array.
     *
     * Complexidade: O(n)
     */
    private void redimensionar() {

        int[] novo = new int[elementos.length * 2];

        // Copia na ordem correta da fila
        for (int i = 0; i < tamanho; i++) {
            novo[i] = elementos[(inicio + i) % elementos.length];
        }

        elementos = novo;
        inicio = 0;
        fim = tamanho;
    }
}