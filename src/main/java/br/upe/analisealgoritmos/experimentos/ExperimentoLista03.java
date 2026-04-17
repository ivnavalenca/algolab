package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoLista03
 * ============================================================
 *
 * OBJETIVO:
 * Executar experimentos adicionais (Lista 03) envolvendo
 * algoritmos e registrar tempos de execução.
 *
 * OBS:
 * Este experimento pode variar conforme a atividade,
 * mas segue o padrão de exportação da pipeline Algolab.
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
import java.util.Random;

import br.upe.analisealgoritmos.utils.CSVExporter;

public class ExperimentoLista03 {

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

        System.out.println("📘 Executando Experimento Lista 03...");

        List<String[]> resultados = new ArrayList<>();

        for (int n : TAMANHOS) {

            int[] dados = gerarDados(n);

            /*
             * ========================================================
             * EXEMPLO: BUSCA LINEAR (PODE ADAPTAR CONFORME SUA LISTA)
             * ========================================================
             */
            int alvo = dados[n / 2];

            long inicio = System.nanoTime();
            buscaLinear(dados, alvo);
            long tempo = System.nanoTime() - inicio;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "lista03",
                    "Linear",
                    String.valueOf(tempo)
            });

            /*
             * 👉 Você pode adicionar mais algoritmos aqui
             */
        }

        /*
         * ============================================================
         * 🔥 EXPORTAÇÃO PADRÃO (PIPELINE)
         * ============================================================
         */
        CSVExporter.salvarPipeline(resultados);

        System.out.println("✅ Experimento Lista 03 concluído!");
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