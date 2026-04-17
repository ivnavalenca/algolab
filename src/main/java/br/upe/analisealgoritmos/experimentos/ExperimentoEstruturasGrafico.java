package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoEstruturasGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Benchmark de estruturas de dados (inserção + busca).
 *
 * ESTRUTURAS:
 * ✔ ArrayList
 * ✔ LinkedList
 * ✔ HashSet
 * ✔ TreeSet
 *
 * PADRÃO:
 * ✔ Interface Experimento
 * ✔ GeradorDados
 * ✔ ConfigBenchmark
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;
import br.upe.analisealgoritmos.utils.dados.GeradorDados;

public class ExperimentoEstruturasGrafico implements Experimento {

    @Override
    public String getNome() {
        return "Estruturas";
    }

    @Override
    public List<String[]> executar() {

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            int[] dados = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ALEATORIO);

            testarArrayList(dados, resultados, n);
            testarLinkedList(dados, resultados, n);
            testarHashSet(dados, resultados, n);
            testarTreeSet(dados, resultados, n);
        }

        return resultados;
    }

    /*
     * ============================================================
     * ARRAYLIST
     * ============================================================
     */
    private void testarArrayList(int[] dados, List<String[]> resultados, int n) {

        List<Integer> lista = new ArrayList<>();

        long inicio = System.nanoTime();

        for (int v : dados) lista.add(v);
        for (int v : dados) lista.contains(v);

        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "ArrayList", tempo);
    }

    /*
     * ============================================================
     * LINKEDLIST
     * ============================================================
     */
    private void testarLinkedList(int[] dados, List<String[]> resultados, int n) {

        List<Integer> lista = new LinkedList<>();

        long inicio = System.nanoTime();

        for (int v : dados) lista.add(v);
        for (int v : dados) lista.contains(v);

        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "LinkedList", tempo);
    }

    /*
     * ============================================================
     * HASHSET
     * ============================================================
     */
    private void testarHashSet(int[] dados, List<String[]> resultados, int n) {

        Set<Integer> set = new HashSet<>();

        long inicio = System.nanoTime();

        for (int v : dados) set.add(v);
        for (int v : dados) set.contains(v);

        long tempo = System.nanoTime() - inicio;

        registrar(resultados, n, "HashSet", tempo);
    }

    /*
     * ============================================================
     * TREESET
     * ============================================================
     */
    private void testarTreeSet(int[] dados, List<String[]> resultados, int n) {

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
    private void registrar(List<String[]> resultados, int n, String nome, long tempo) {

        resultados.add(new String[]{
                String.valueOf(n),
                "aleatorio",
                nome,
                String.valueOf(tempo)
        });
    }
}