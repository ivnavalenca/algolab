package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * IMPORTS
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

import br.upe.analisealgoritmos.estruturas.acesso_direto.TabelaHashLinearProbing;
import br.upe.analisealgoritmos.estruturas.lineares.VetorDinamico;
import br.upe.analisealgoritmos.utils.CSVExporter;

/*
 * ============================================================
 * CLASSE: ExperimentoEstruturasGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Avaliar desempenho de estruturas de dados.
 *
 * ESTRUTURAS:
 * - VetorDinâmico
 * - Hash (Linear Probing)
 *
 * MÉTRICA:
 * Tempo de inserção
 *
 * ============================================================
 */

public class ExperimentoEstruturasGrafico {

    /*
     * ============================================================
     * MÉTODO PRINCIPAL
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
         * LOOP PRINCIPAL
         * ============================================================
         */
        for (int n : tamanhos) {

            /*
             * ============================================================
             * TESTE: Vetor Dinâmico
             * ============================================================
             */
            VetorDinamico vetor = new VetorDinamico();

            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                vetor.adicionar(i);
            }

            long fim = System.nanoTime();

            resultados.add(new String[]{
                    String.valueOf(n),
                    "insercao",
                    "VetorDinamico",
                    String.valueOf(fim - inicio)
            });

            /*
             * ============================================================
             * TESTE: Hash Linear Probing
             * ============================================================
             */
            TabelaHashLinearProbing hash = new TabelaHashLinearProbing(n);

            inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                hash.inserir(i);
            }

            fim = System.nanoTime();

            resultados.add(new String[]{
                    String.valueOf(n),
                    "insercao",
                    "HashLinearProbing",
                    String.valueOf(fim - inicio)
            });
        }

        /*
         * ============================================================
         * EXPORTAÇÃO
         * ============================================================
         */
        CSVExporter.salvar("resultados/estruturas.csv", resultados);

        System.out.println("✅ Experimento de estruturas finalizado.");
    }
}