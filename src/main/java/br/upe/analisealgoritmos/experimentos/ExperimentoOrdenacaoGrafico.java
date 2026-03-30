package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * EXPERIMENTO: ORDENAÇÃO
 * ============================================================
 *
 * OBJETIVO:
 * Comparar algoritmos de ordenação
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

import br.upe.analisealgoritmos.ordenacao.BubbleSort;
import br.upe.analisealgoritmos.ordenacao.InsertionSort;
import br.upe.analisealgoritmos.ordenacao.MergeSort;
import br.upe.analisealgoritmos.ordenacao.Ordenador;
import br.upe.analisealgoritmos.ordenacao.QuickSort;
import br.upe.analisealgoritmos.ordenacao.SelectionSort;
import br.upe.analisealgoritmos.utils.CSVExporter;
import br.upe.analisealgoritmos.utils.GeradorDados;

public class ExperimentoOrdenacaoGrafico {

    public static void executar() {

        int[] tamanhos = {100, 1000, 5000};
        List<String[]> resultados = new ArrayList<>();

        List<Ordenador> algoritmos = List.of(
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new MergeSort(),
                new QuickSort()
        );

        for (int n : tamanhos) {

            int[] vetorBase = GeradorDados.gerarVetorAleatorio(n);

            for (Ordenador algoritmo : algoritmos) {

                int[] copia = vetorBase.clone();

                long inicio = System.nanoTime();
                algoritmo.ordenar(copia);
                long fim = System.nanoTime();

                resultados.add(new String[]{
                        String.valueOf(n),
                        "ordenacao",
                        algoritmo.getClass().getSimpleName(),
                        String.valueOf(fim - inicio)
                });
            }
        }

        /*
         * EXPORTAÇÃO
         */
        CSVExporter.salvar("resultados/ordenacao.csv", resultados);

        // 🔥 ESSENCIAL — histórico
        CSVExporter.salvarComHistorico("resultados", "ordenacao", resultados);

        System.out.println("✅ Experimento de ordenação finalizado.");
    }
}