package br.upe.analisealgoritmos.grafos;

import java.util.LinkedList;
import java.util.Queue;

/*
 * CLASSE: BFS (Breadth-First Search)
 *
 * Percorre o grafo em largura (por níveis).
 *
 * Ideia:
 * - Começa por um vértice inicial
 * - Visita todos os vizinhos
 * - Depois os vizinhos dos vizinhos
 *
 * Estrutura usada:
 * - Fila (Queue)
 *
 * Complexidade:
 * - Tempo: O(V + E)
 * - Espaço: O(V)
 */

public class BFS {

    /*
     * Executa BFS a partir de um vértice inicial
     */
    public static void executar(Grafo g, int inicio) {

        int n = g.getNumeroVertices();

        boolean[] visitado = new boolean[n];

        Queue<Integer> fila = new LinkedList<>();

        /*
         * Inicialização
         */
        visitado[inicio] = true;
        fila.add(inicio);

        /*
         * Processo principal
         */
        while (!fila.isEmpty()) {

            int v = fila.poll();

            System.out.print(v + " ");

            /*
             * Visita vizinhos
             */
            for (int vizinho : g.getAdj().get(v)) {

                if (!visitado[vizinho]) {

                    visitado[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }

        /*
         * MODELAGEM MATEMÁTICA:
         *
         * Cada vértice é visitado uma vez → V
         * Cada aresta é percorrida uma vez → E
         *
         * T(n) = V + E
         */
    }

    /*
     * Exemplo de uso
     */
    public static void main(String[] args) {

        Grafo g = new Grafo(5);

        g.adicionarAresta(0, 1);
        g.adicionarAresta(0, 2);
        g.adicionarAresta(1, 3);
        g.adicionarAresta(2, 4);

        System.out.println("BFS a partir do vértice 0:");

        executar(g, 0);
    }
}