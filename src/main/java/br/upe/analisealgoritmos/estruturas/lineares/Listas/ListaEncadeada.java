package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * CLASSE: ListaEncadeada
 *
 * Implementação simples de lista encadeada
 *
 * Usada para:
 * - colisões em tabela hash (encadeamento)
 *
 * Complexidade:
 * - Inserção: O(1)
 * - Busca: O(n)
 */

public class ListaEncadeada {

    /*
     * Nó da lista
     */
    private class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    private No inicio;

    /*
     * Insere no início
     * O(1)
     */
    public void inserir(int valor) {

        No novo = new No(valor);
        novo.proximo = inicio;
        inicio = novo;
    }

    /*
     * Busca elemento
     * O(n)
     */
    public boolean buscar(int valor) {

        No atual = inicio;

        while (atual != null) {

            if (atual.valor == valor) {
                return true;
            }

            atual = atual.proximo;
        }

        return false;
    }

    /*
     * Remove elemento
     * O(n)
     */
    public void remover(int valor) {

        No atual = inicio;
        No anterior = null;

        while (atual != null) {

            if (atual.valor == valor) {

                if (anterior == null) {
                    inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }

                return;
            }

            anterior = atual;
            atual = atual.proximo;
        }
    }
}