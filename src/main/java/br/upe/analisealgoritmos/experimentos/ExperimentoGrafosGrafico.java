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
 * ✔ BFS (Busca em Largura)
 * ✔ DFS (Busca em Profundidade)
 *
 * PADRÃO:
 * ✔ Interface Experimento
 * ✔ ConfigBenchmark
 *
 * OBS:
 * Grafo simples gerado automaticamente (lista de adjacência)
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;

public class ExperimentoGrafosGrafico implements Experimento {

    @Override
    public String getNome() {
        return "Grafos";
    }

    @Override
    public List<String[]> executar() {

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            Map<Integer, List<Integer>> grafo = gerarGrafo(n);

            /*
             * ========================================================
             * BFS
             * ========================================================
             */
            long inicio = System.nanoTime();
            bfs(grafo, 0);
            long tempoBFS = System.nanoTime() - inicio;

            /*
             * ========================================================
             * DFS
             * ========================================================
             */
            inicio = System.nanoTime();
            dfs(grafo, 0, new HashSet<>());
            long tempoDFS = System.nanoTime() - inicio;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "grafo",
                    "BFS",
                    String.valueOf(tempoBFS)
            });

            resultados.add(new String[]{
                    String.valueOf(n),
                    "grafo",
                    "DFS",
                    String.valueOf(tempoDFS)
            });
        }

        return resultados;
    }

    /*
     * ============================================================
     * GERADOR DE GRAFO
     * ============================================================
     */
    private Map<Integer, List<Integer>> gerarGrafo(int n) {

        Map<Integer, List<Integer>> grafo = new HashMap<>();

        for (int i = 0; i < n; i++) {
            grafo.put(i, new ArrayList<>());

            // conecta com anterior (estrutura linear)
            if (i > 0) {
                grafo.get(i).add(i - 1);
                grafo.get(i - 1).add(i);
            }
        }

        return grafo;
    }

    /*
     * ============================================================
     * BFS
     * ============================================================
     */
    private void bfs(Map<Integer, List<Integer>> grafo, int inicio) {

        Queue<Integer> fila = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();

        fila.add(inicio);

        while (!fila.isEmpty()) {

            int atual = fila.poll();

            if (!visitados.add(atual)) continue;

            for (int vizinho : grafo.get(atual)) {
                fila.add(vizinho);
            }
        }
    }

    /*
     * ============================================================
     * DFS
     * ============================================================
     */
    private void dfs(Map<Integer, List<Integer>> grafo, int no, Set<Integer> visitados) {

        if (!visitados.add(no)) return;

        for (int vizinho : grafo.get(no)) {
            dfs(grafo, vizinho, visitados);
        }
    }
}