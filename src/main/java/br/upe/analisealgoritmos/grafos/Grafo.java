package br.upe.analisealgoritmos.grafos;

import java.util.ArrayList;

/*
 * CLASSE: Grafo
 *
 * Representação de grafo usando lista de adjacência.
 *
 * Cada vértice possui uma lista de vizinhos.
 *
 * Complexidade:
 * - Inserção de aresta: O(1)
 * - Espaço: O(V + E)
 */

public class Grafo {

    private ArrayList<ArrayList<Integer>> adj;

    /*
     * Construtor
     *
     * Cria um grafo com 'vertices' vértices
     */
    public Grafo(int vertices) {

        adj = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    /*
     * Adiciona uma aresta ao grafo
     *
     * Complexidade: O(1)
     */
    public void adicionarAresta(int origem, int destino) {
        adj.get(origem).add(destino);
    }

    /*
     * Retorna a lista de adjacência
     */
    public ArrayList<ArrayList<Integer>> getAdj() {
        return adj;
    }

    /*
     * Retorna o número de vértices
     */
    public int getNumeroVertices() {
        return adj.size();
    }

    /*
     * Exibe o grafo (para debug)
     */
    public void imprimir() {

        for (int i = 0; i < adj.size(); i++) {

            System.out.print("Vértice " + i + ": ");

            for (int vizinho : adj.get(i)) {
                System.out.print(vizinho + " ");
            }

            System.out.println();
        }
    }
}