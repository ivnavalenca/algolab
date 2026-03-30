package br.upe.analisealgoritmos.grafos;

/*
 * CLASSE: DFS (Depth-First Search)
 *
 * Percorre o grafo em profundidade.
 *
 * Ideia:
 * - Vai o mais fundo possível antes de voltar
 *
 * Estrutura usada:
 * - Recursão (ou pilha)
 *
 * Complexidade:
 * - Tempo: O(V + E)
 * - Espaço: O(V)
 */

public class DFS {

    /*
     * Método principal
     */
    public static void executar(Grafo g, int inicio) {

        int n = g.getNumeroVertices();

        boolean[] visitado = new boolean[n];

        dfs(g, inicio, visitado);
    }

    /*
     * Método recursivo
     */
    private static void dfs(Grafo g, int v, boolean[] visitado) {

        visitado[v] = true;

        System.out.print(v + " ");

        /*
         * Visita vizinhos
         */
        for (int vizinho : g.getAdj().get(v)) {

            if (!visitado[vizinho]) {
                dfs(g, vizinho, visitado);
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

        System.out.println("DFS a partir do vértice 0:");

        executar(g, 0);
    }
}