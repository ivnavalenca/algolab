package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoGrafosGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Benchmark de algoritmos em grafos.
 *
 * ALGORITMOS:
 * ✔ BFS
 * ✔ DFS (iterativo)
 * ✔ Dijkstra (grafo ponderado)
 *
 * MELHORIAS:
 * ✔ Grafo aleatório
 * ✔ Controle de densidade
 * ✔ Sem recursão profunda
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;

public class ExperimentoGrafosGrafico implements Experimento {

    private static final Random RANDOM = new Random(42);

    @Override
    public String getNome() {
        return "Grafos";
    }

    @Override
    public List<String[]> executar() {

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            Map<Integer, List<Aresta>> grafo = gerarGrafo(n, n * 2);

            /*
             * ========================================================
             * BFS
             * ========================================================
             */
            long ini = System.nanoTime();
            bfs(grafo, 0);
            long tempoBFS = System.nanoTime() - ini;

            /*
             * ========================================================
             * DFS ITERATIVO
             * ========================================================
             */
            ini = System.nanoTime();
            dfsIterativo(grafo, 0);
            long tempoDFS = System.nanoTime() - ini;

            /*
             * ========================================================
             * DIJKSTRA
             * ========================================================
             */
            ini = System.nanoTime();
            dijkstra(grafo, 0);
            long tempoDijkstra = System.nanoTime() - ini;

            resultados.add(new String[]{n+"","grafo","BFS",tempoBFS+""});
            resultados.add(new String[]{n+"","grafo","DFS",tempoDFS+""});
            resultados.add(new String[]{n+"","grafo","Dijkstra",tempoDijkstra+""});
        }

        return resultados;
    }

    /*
     * ============================================================
     * GERA GRAFO ALEATÓRIO (PONDERADO)
     * ============================================================
     */
    private Map<Integer, List<Aresta>> gerarGrafo(int n, int arestas) {

        Map<Integer, List<Aresta>> grafo = new HashMap<>();

        for (int i = 0; i < n; i++) {
            grafo.put(i, new ArrayList<>());
        }

        // garante conectividade
        for (int i = 1; i < n; i++) {
            int peso = RANDOM.nextInt(10) + 1;
            grafo.get(i).add(new Aresta(i - 1, peso));
            grafo.get(i - 1).add(new Aresta(i, peso));
        }

        // adiciona arestas aleatórias
        for (int i = 0; i < arestas; i++) {

            int u = RANDOM.nextInt(n);
            int v = RANDOM.nextInt(n);

            if (u != v) {
                int peso = RANDOM.nextInt(10) + 1;
                grafo.get(u).add(new Aresta(v, peso));
                grafo.get(v).add(new Aresta(u, peso));
            }
        }

        return grafo;
    }

    /*
     * ============================================================
     * BFS
     * ============================================================
     */
    private void bfs(Map<Integer, List<Aresta>> grafo, int inicio) {

        Queue<Integer> fila = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();

        fila.add(inicio);

        while (!fila.isEmpty()) {

            int atual = fila.poll();

            if (!visitados.add(atual)) continue;

            for (Aresta a : grafo.get(atual)) {
                fila.add(a.destino);
            }
        }
    }

    /*
     * ============================================================
     * DFS ITERATIVO
     * ============================================================
     */
    private void dfsIterativo(Map<Integer, List<Aresta>> grafo, int inicio) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visitados = new HashSet<>();

        stack.push(inicio);

        while (!stack.isEmpty()) {

            int atual = stack.pop();

            if (!visitados.add(atual)) continue;

            for (Aresta a : grafo.get(atual)) {
                stack.push(a.destino);
            }
        }
    }

    /*
     * ============================================================
     * DIJKSTRA
     * ============================================================
     */
    private void dijkstra(Map<Integer, List<Aresta>> grafo, int origem) {

        Map<Integer, Integer> dist = new HashMap<>();

        for (int v : grafo.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
        }

        dist.put(origem, 0);

        PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));
        pq.add(new Aresta(origem, 0));

        while (!pq.isEmpty()) {

            Aresta atual = pq.poll();

            for (Aresta viz : grafo.get(atual.destino)) {

                int novaDist = dist.get(atual.destino) + viz.peso;

                if (novaDist < dist.get(viz.destino)) {
                    dist.put(viz.destino, novaDist);
                    pq.add(new Aresta(viz.destino, novaDist));
                }
            }
        }
    }

    /*
     * ============================================================
     * CLASSE ARESTA
     * ============================================================
     */
    private static class Aresta {
        int destino;
        int peso;

        Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
}