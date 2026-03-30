package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * IMPORTS
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.upe.analisealgoritmos.ordenacao.BubbleSort;
import br.upe.analisealgoritmos.ordenacao.InsertionSort;
import br.upe.analisealgoritmos.ordenacao.MergeSort;
import br.upe.analisealgoritmos.ordenacao.Ordenador;
import br.upe.analisealgoritmos.ordenacao.QuickSort;
import br.upe.analisealgoritmos.ordenacao.SelectionSort;
import br.upe.analisealgoritmos.utils.CSVExporter;
import br.upe.analisealgoritmos.utils.GeradorDados;

/*
 * ============================================================
 * CLASSE: ExperimentoOrdenacaoCasosGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Avaliar algoritmos em diferentes cenários:
 * - aleatório
 * - ordenado
 * - reverso
 *
 * ============================================================
 */

public class ExperimentoOrdenacaoCasosGrafico {

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

            Map<String, int[]> cenarios = Map.of(
                    "aleatorio", GeradorDados.gerarVetorAleatorio(n),
                    "ordenado", GeradorDados.gerarVetorOrdenado(n),
                    "reverso", GeradorDados.gerarVetorReverso(n)
            );

            for (Map.Entry<String, int[]> entry : cenarios.entrySet()) {

                String cenario = entry.getKey();
                int[] base = entry.getValue();

                for (Ordenador algoritmo : algoritmos) {

                    int[] vetor = Arrays.copyOf(base, base.length);

                    long inicio = System.nanoTime();

                    algoritmo.ordenar(vetor);

                    long fim = System.nanoTime();

                    resultados.add(new String[]{
                            String.valueOf(n),
                            cenario,
                            algoritmo.nome(), // ✔ CORREÇÃO AQUI
                            String.valueOf(fim - inicio)
                    });
                }
            }
        }

        CSVExporter.salvar("resultados/ordenacao_casos.csv", resultados);

        System.out.println("✅ Experimento de ordenação por casos finalizado.");
    }
}