package br.upe.analisealgoritmos.experimentos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/*
 * ============================================================
 * EXPERIMENTO DE ESTRUTURAS DE DADOS
 * ============================================================
 *
 * OBJETIVO:
 *
 * Comparar o desempenho de:
 *
 * 1. Busca em vetor (acesso sequencial)
 * 2. Busca em tabela hash (acesso direto)
 *
 * ============================================================
 * CONCEITO FUNDAMENTAL
 * ============================================================
 *
 * Estruturas de dados influenciam diretamente o desempenho.
 *
 * NÃO é só o algoritmo que importa!
 *
 * ============================================================
 * MODELOS TEÓRICOS
 * ============================================================
 *
 * Vetor (Busca Linear):
 *   T(n) = n → O(n)
 *
 * Tabela Hash:
 *   T(n) ≈ 1 → O(1) (caso médio)
 *   Pior caso: O(n)
 *
 * ============================================================
 * IDEIA DO EXPERIMENTO
 * ============================================================
 *
 * Para cada tamanho n:
 *
 * 1. Geramos dados aleatórios
 * 2. Testamos busca em vetor
 * 3. Testamos busca em hash
 * 4. Medimos tempo médio
 * 5. Plotamos gráfico
 *
 * ============================================================
 */

public class ExperimentoEstruturasGrafico {

    /*
     * Número de execuções para média
     */
    private static final int REPETICOES = 10;

    public static void executar() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int[] tamanhos = {1000, 2000, 4000, 8000, 16000};

        for (int n : tamanhos) {

            int[] vetor = gerarVetor(n);

            /*
             * Estrutura de acesso direto (Hash)
             */
            HashSet<Integer> hash = new HashSet<>();

            for (int v : vetor) {
                hash.add(v);
            }

            /*
             * CASO MÉDIO (chave existente)
             */
            int chave = vetor[new Random().nextInt(n)];

            /*
             * ========================================================
             * BUSCA EM VETOR (O(n))
             * ========================================================
             */
            long tempoLinear = medirTempo(() -> buscaLinear(vetor, chave));

            /*
             * ========================================================
             * BUSCA EM HASH (O(1) médio)
             * ========================================================
             */
            long tempoHash = medirTempo(() -> hash.contains(chave));

            /*
             * ========================================================
             * INSERÇÃO (IMPORTANTE!)
             * ========================================================
             *
             * Mostra custo de construir a estrutura
             */
            long tempoInsercaoHash = medirTempo(() -> {
                HashSet<Integer> temp = new HashSet<>();
                for (int v : vetor) temp.add(v);
            });

            /*
             * Armazena no gráfico
             */
            dataset.addValue(tempoLinear, "Vetor (O(n))", String.valueOf(n));
            dataset.addValue(tempoHash, "Hash (O(1))", String.valueOf(n));
            dataset.addValue(tempoInsercaoHash, "Construção Hash (O(n))", String.valueOf(n));

            /*
             * Saída para análise
             */
            System.out.println(
                    "n=" + n +
                    " | Vetor=" + tempoLinear +
                    " | Hash=" + tempoHash +
                    " | InserçãoHash=" + tempoInsercaoHash
            );
        }

        /*
         * ========================================================
         * GRÁFICO
         * ========================================================
         */
        JFreeChart grafico = ChartFactory.createLineChart(
                "Estruturas: Vetor vs Hash",
                "Tamanho da entrada (n)",
                "Tempo médio (ns)",
                dataset
        );

        GraficoUtils.salvarGrafico(grafico, "estruturas_didatico");
    }

    /*
     * ============================================================
     * MÉTODO DE MEDIÇÃO
     * ============================================================
     *
     * Estratégia:
     * - Executa várias vezes
     * - Remove extremos
     * - Calcula média
     */
    private static long medirTempo(Runnable algoritmo) {

        long[] tempos = new long[REPETICOES];

        for (int i = 0; i < REPETICOES; i++) {

            long inicio = System.nanoTime();

            algoritmo.run();

            long fim = System.nanoTime();

            tempos[i] = fim - inicio;
        }

        Arrays.sort(tempos);

        long soma = 0;

        for (int i = 1; i < tempos.length - 1; i++) {
            soma += tempos[i];
        }

        return soma / (REPETICOES - 2);
    }

    /*
     * ============================================================
     * BUSCA LINEAR
     * ============================================================
     *
     * Percorre todos os elementos
     *
     * Complexidade:
     * - Melhor: O(1)
     * - Médio: O(n)
     * - Pior: O(n)
     */
    private static boolean buscaLinear(int[] v, int chave) {

        for (int x : v) {
            if (x == chave) return true;
        }

        return false;
    }

    /*
     * ============================================================
     * GERAÇÃO DE DADOS
     * ============================================================
     */
    private static int[] gerarVetor(int n) {

        Random r = new Random();

        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = r.nextInt(n);
        }

        return v;
    }
}