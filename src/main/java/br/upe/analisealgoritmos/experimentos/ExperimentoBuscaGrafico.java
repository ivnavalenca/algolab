package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoBuscaGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Executar experimentos de algoritmos de busca e registrar
 * o tempo de execução para análise posterior.
 *
 * ALGORITMOS:
 * ✔ Busca Linear
 * ✔ Busca Binária
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
import java.util.List;

import br.upe.analisealgoritmos.utils.CSVExporter;

public class ExperimentoBuscaGrafico {

    /*
     * ============================================================
     * TAMANHOS DE ENTRADA
     * ============================================================
     */
    private static final int[] TAMANHOS = {100, 500, 1000, 2000, 5000};

    /*
     * ============================================================
     * EXECUÇÃO PRINCIPAL
     * ============================================================
     */
    public static void main(String[] args) {

        System.out.println("🔍 Executando experimento de busca...");

        List<String[]> resultados = new ArrayList<>();

        for (int n : TAMANHOS) {

            int[] array = gerarArrayOrdenado(n);
            int alvo = array[n / 2];

            /*
             * ========================================================
             * BUSCA LINEAR
             * ========================================================
             */
            long inicio = System.nanoTime();
            buscaLinear(array, alvo);
            long tempoLinear = System.nanoTime() - inicio;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "medio",
                    "Linear",
                    String.valueOf(tempoLinear)
            });

            /*
             * ========================================================
             * BUSCA BINÁRIA
             * ========================================================
             */
            inicio = System.nanoTime();
            buscaBinaria(array, alvo);
            long tempoBinaria = System.nanoTime() - inicio;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "medio",
                    "Binaria",
                    String.valueOf(tempoBinaria)
            });
        }

        /*
         * ============================================================
         * 🔥 EXPORTAÇÃO PADRÃO (PIPELINE)
         * ============================================================
         */
        CSVExporter.salvarPipeline(resultados);

        System.out.println("✅ Experimento de busca concluído!");
    }

    /*
     * ============================================================
     * BUSCA LINEAR
     * ============================================================
     */
    private static int buscaLinear(int[] array, int alvo) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == alvo) return i;
        }

        return -1;
    }

    /*
     * ============================================================
     * BUSCA BINÁRIA
     * ============================================================
     */
    private static int buscaBinaria(int[] array, int alvo) {

        int esquerda = 0;
        int direita = array.length - 1;

        while (esquerda <= direita) {

            int meio = (esquerda + direita) / 2;

            if (array[meio] == alvo) return meio;

            if (array[meio] < alvo) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }

    /*
     * ============================================================
     * GERAR ARRAY ORDENADO
     * ============================================================
     */
    private static int[] gerarArrayOrdenado(int n) {

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        return array;
    }
}