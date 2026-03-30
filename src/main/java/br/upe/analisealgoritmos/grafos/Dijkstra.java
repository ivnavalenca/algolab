package br.upe.analisealgoritmos.grafos;

import java.util.Arrays;

/*
 * CLASSE: Dijkstra
 *
 * Calcula o menor caminho a partir de um vértice.
 */

public class Dijkstra {

    /*
     * Complexidade:
     * O(V²) (versão simples)
     */
    public static void executar(int[][] grafo, int origem) {

        int V = grafo.length;

        int[] dist = new int[V];
        boolean[] visitado = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origem] = 0;

        for (int i = 0; i < V - 1; i++) {

            int u = minDist(dist, visitado);

            visitado[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visitado[v] &&
                    grafo[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + grafo[u][v] < dist[v]) {

                    dist[v] = dist[u] + grafo[u][v];
                }
            }
        }

        System.out.println("Distâncias: " + Arrays.toString(dist));

        /*
         * MODELAGEM:
         *
         * T(n) = V²
         */
    }

    private static int minDist(int[] dist, boolean[] visitado) {

        int min = Integer.MAX_VALUE, indice = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visitado[v] && dist[v] < min) {
                min = dist[v];
                indice = v;
            }
        }

        return indice;
    }
}