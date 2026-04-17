package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoOrdenacaoGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Benchmark de algoritmos de ordenação.
 *
 * ALGORITMOS:
 * ✔ Bubble Sort
 * ✔ Selection Sort
 * ✔ Insertion Sort
 * ✔ Merge Sort
 * ✔ Quick Sort
 *
 * PADRÃO:
 * ✔ Interface Experimento
 * ✔ GeradorDados
 * ✔ ConfigBenchmark
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;
import br.upe.analisealgoritmos.utils.dados.GeradorDados;

public class ExperimentoOrdenacaoGrafico implements Experimento {

    @Override
    public String getNome() {
        return "Ordenacao";
    }

    @Override
    public List<String[]> executar() {

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            int[] base = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ALEATORIO);

            executarAlgoritmo("Bubble", base, resultados, n);
            executarAlgoritmo("Selection", base, resultados, n);
            executarAlgoritmo("Insertion", base, resultados, n);
            executarAlgoritmo("Merge", base, resultados, n);
            executarAlgoritmo("Quick", base, resultados, n);
        }

        return resultados;
    }

    /*
     * ============================================================
     * EXECUÇÃO PADRÃO
     * ============================================================
     */
    private void executarAlgoritmo(String nome, int[] base, List<String[]> resultados, int n) {

        int[] copia = Arrays.copyOf(base, base.length);

        long inicio = System.nanoTime();

        switch (nome) {
            case "Bubble": bubbleSort(copia); break;
            case "Selection": selectionSort(copia); break;
            case "Insertion": insertionSort(copia); break;
            case "Merge": mergeSort(copia, 0, copia.length - 1); break;
            case "Quick": quickSort(copia, 0, copia.length - 1); break;
        }

        long tempo = System.nanoTime() - inicio;

        resultados.add(new String[]{
                String.valueOf(n),
                "aleatorio",
                nome,
                String.valueOf(tempo)
        });
    }

    /*
     * ============================================================
     * ALGORITMOS
     * ============================================================
     */

    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
    }

    private void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[min]) min = j;

            int t = arr[min];
            arr[min] = arr[i];
            arr[i] = t;
        }
    }

    private void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2)
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        int t = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = t;

        return i + 1;
    }
}