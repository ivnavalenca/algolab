package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * IMPORTS
 * ============================================================
 */

import br.upe.analisealgoritmos.ordenacao.*;
import br.upe.analisealgoritmos.utils.GeradorDados;
import br.upe.analisealgoritmos.utils.CSVExporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/*
 * ============================================================
 * CLASSE: ExperimentoOrdenacaoGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Comparar desempenho de algoritmos de ordenação.
 *
 * ALGORITMOS:
 * - BubbleSort
 * - InsertionSort
 * - SelectionSort
 * - MergeSort
 * - QuickSort
 *
 * SAÍDA:
 * CSV com resultados para geração de gráficos.
 *
 * ============================================================
 */

public class ExperimentoOrdenacaoGrafico {

    /*
     * ============================================================
     * MÉTODO PRINCIPAL DO EXPERIMENTO
     * ============================================================
     */
    public static void executar() {

        /*
         * ============================================================
         * CONFIGURAÇÃO
         * ============================================================
         */
        int[] tamanhos = {100, 1000, 5000};
        List<String[]> resultados = new ArrayList<>();

        /*
         * ============================================================
         * LISTA DE ALGORITMOS
         * ============================================================
         */
        List<Ordenador> algoritmos = List.of(
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new MergeSort(),
                new QuickSort()
        );

        /*
         * ============================================================
         * LOOP PRINCIPAL
         * ============================================================
         */
        for (int n : tamanhos) {

            /*
             * ============================================================
             * GERAÇÃO DO VETOR BASE
             * ============================================================
             */
            int[] vetorBase = GeradorDados.gerarVetorAleatorio(n);

            /*
             * ============================================================
             * EXECUÇÃO DOS ALGORITMOS
             * ============================================================
             */
            for (Ordenador algoritmo : algoritmos) {

                /*
                 * IMPORTANTE:
                 * Clonar vetor para evitar interferência entre algoritmos
                 */
                int[] vetor = Arrays.copyOf(vetorBase, vetorBase.length);

                long inicio = System.nanoTime();

                algoritmo.ordenar(vetor);

                long fim = System.nanoTime();

                resultados.add(new String[]{
                        String.valueOf(n),
                        "ordenacao",
                        algoritmo.nome(),
                        String.valueOf(fim - inicio)
                });
            }
        }

        /*
         * ============================================================
         * EXPORTAÇÃO DOS RESULTADOS
         * ============================================================
         */
        CSVExporter.salvar("resultados/ordenacao.csv", resultados);

        System.out.println("✅ Experimento de ordenação finalizado.");
    }
}