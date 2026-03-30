package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * EXPERIMENTO: ESTRUTURAS DE DADOS
 * ============================================================
 *
 * OBJETIVO:
 * Comparar desempenho de inserção em estruturas
 *
 * ============================================================
 */

import br.upe.analisealgoritmos.estruturas.acesso_direto.VetorDinamico;
import br.upe.analisealgoritmos.estruturas.acesso_direto.TabelaHashLinearProbing;
import br.upe.analisealgoritmos.utils.CSVExporter;

import java.util.ArrayList;
import java.util.List;

public class ExperimentoEstruturasGrafico {

    public static void executar() {

        int[] tamanhos = {100, 1000, 5000};
        List<String[]> resultados = new ArrayList<>();

        for (int n : tamanhos) {

            /*
             * VETOR DINÂMICO
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
             * HASH LINEAR PROBING
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
         * EXPORTAÇÃO
         */
        CSVExporter.salvar("resultados/estruturas.csv", resultados);

        // 🔥 ESSENCIAL — histórico
        CSVExporter.salvarComHistorico("resultados", "estruturas", resultados);

        System.out.println("✅ Experimento de estruturas finalizado.");
    }
}