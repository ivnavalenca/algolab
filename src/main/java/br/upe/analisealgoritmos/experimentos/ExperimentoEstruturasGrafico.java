package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoEstruturasGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Avaliar o desempenho de diferentes estruturas de dados
 * (inserção + busca) e registrar tempos de execução.
 *
 * ESTRUTURAS:
 * ✔ ArrayList
 * ✔ LinkedList
 * ✔ HashSet
 * ✔ TreeSet
 *
 * SAÍDA:
 * ✔ resultados/latest.csv
 * ✔ resultados/historico/run_<timestamp>.csv
 *
 * INTEGRAÇÃO:
 * ✔ CSVExporter.salvarPipeline()
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import br.upe.analisealgoritmos.utils.CSVExporter;

public class ExperimentoEstruturasGrafico {

    /*
     * ============================================================
     * TAMANHOS DE ENTRADA
     * ============================================================
     */
    private static final int[] TAMANHOS = {100, 500, 1000, 2000, 5000};

    private static final Random random = new Random();

    /*
     * ============================================================
     * EXECUÇÃO PRINCIPAL
     * ============================================================
     */
    public static void main(String[] args) {

        System.out.println("🧱 Executando experimento de estruturas de dados...");

        List<String[]> resultados = new ArrayList<>();

        for (int n : TAMANHOS) {

            int[] dados = gerarDados(n);

            testarArrayList(dados, resultados, n);
            testarLinkedList(dados, resultados, n);
            testarHashSet(dados, resultados, n);
            testarTreeSet(dados, resultados, n);
        }

        /*
         * ============================================================
         * 🔥 EXPORTAÇÃO PADRÃO (PIPELINE)
         * ============================================================
         */
        CSVExporter.salvarPipeline(resultados);

        System.out.println("✅ Experimento de estruturas concluído!");
    }

    /*
     * ============================================================
     * TESTES
     * ============================================================
     */

    private static void testarArrayList(int[] dados, List<String[]> resultados, int n) {

        List<Integer> lista = new ArrayList<>();

        long inicio = System.nanoTime();
        for (int v : dados) lista.add(v);
        for (int v : dados) lista.contains(v);
        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "ArrayList", tempo);
    }

    private static void testarLinkedList(int[] dados, List<String[]> resultados, int n) {

        List<Integer> lista = new LinkedList<>();

        long inicio = System.nanoTime();
        for (int v : dados) lista.add(v);
        for (int v : dados) lista.contains(v);
        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "LinkedList", tempo);
    }

    private static void testarHashSet(int[] dados, List<String[]> resultados, int n) {

        Set<Integer> set = new HashSet<>();

        long inicio = System.nanoTime();
        for (int v : dados) set.add(v);
        for (int v : dados) set.contains(v);
        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "HashSet", tempo);
    }

    private static void testarTreeSet(int[] dados, List<String[]> resultados, int n) {

        Set<Integer> set = new TreeSet<>();

        long inicio = System.nanoTime();
        for (int v : dados) set.add(v);
        for (int v : dados) set.contains(v);
        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "TreeSet", tempo);
    }

    /*
     * ============================================================
     * REGISTRO PADRÃO
     * ============================================================
     */
    private static void registrar(List<String[]> resultados, int n, String nome, long tempo) {

        resultados.add(new String[]{
                String.valueOf(n),
                "aleatorio",
                nome,
                String.valueOf(tempo)
        });
    }

    /*
     * ============================================================
     * GERAR DADOS ALEATÓRIOS
     * ============================================================
     */
    private static int[] gerarDados(int n) {

        int[] dados = new int[n];

        for (int i = 0; i < n; i++) {
            dados[i] = random.nextInt(n);
        }

        return dados;
    }
}