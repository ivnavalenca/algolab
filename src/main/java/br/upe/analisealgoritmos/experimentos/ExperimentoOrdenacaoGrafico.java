package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoOrdenacaoGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Executar experimentos de algoritmos de ordenação e registrar
 * o tempo de execução para análise posterior.
 *
 * ALGORITMOS:
 * ✔ Bubble Sort
 * ✔ Selection Sort
 * ✔ Insertion Sort
 * ✔ Merge Sort
 * ✔ Quick Sort
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.upe.analisealgoritmos.utils.CSVExporter;

public class ExperimentoOrdenacaoGrafico {

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

        System.out.println("📊 Executando experimento de ordenação...");

        List<String[]> resultados = new ArrayList<>();

        for (int n : TAMANHOS) {

            int[] base = gerarArrayAleatorio(n);

            executarAlgoritmo("Bubble", base, resultados, n);
            executarAlgoritmo("Selection", base, resultados, n);
            executarAlgoritmo("Insertion", base, resultados, n);
            executarAlgoritmo("Merge", base, resultados, n);
            executarAlgoritmo("Quick", base, resultados, n);
        }

        /*
         * ============================================================
         * 🔥 EXPORTAÇÃO PADRÃO (PIPELINE)
         * ============================================================
         */
        CSVExporter.salvarPipeline(resultados);

        System.out.println("✅ Experimento de ordenação concluído!");
    }

    /*
     * ============================================================
     * EXECUTAR ALGORITMO
     * ============================================================
     */
    private static void executarAlgoritmo(String nome, int[] base, List<String[]> resultados, int n) {

        int[] copia = Arrays.copyOf(base, base.length);

        long inicio = System.nanoTime();

        switch (nome) {
            case "Bubble":
                bubbleSort(copia);
                break;
            case "Selection":
                selectionSort(copia);
                break;
            case "Insertion":
                insertionSort(copia);
                break;
            case "Merge":
                mergeSort(copia, 0, copia.length - 1);
                break;
            case "Quick":
                quickSort(copia, 0, copia.length - 1);
                break;
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
     * ALGORITMOS DE ORDENAÇÃO
     * ============================================================
     */

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int chave = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > chave) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = chave;
        }
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /*
     * ============================================================
     * GERAR ARRAY ALEATÓRIO
     * ============================================================
     */
    private static int[] gerarArrayAleatorio(int n) {

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);
        }

        return array;
    }
}