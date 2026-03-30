package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * EXPERIMENTO: BUSCA
 * ============================================================
 *
 * OBJETIVO:
 * Comparar desempenho entre Busca Linear e Binária
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.upe.analisealgoritmos.busca.BuscaBinaria;
import br.upe.analisealgoritmos.busca.BuscaLinear;
import br.upe.analisealgoritmos.busca.Buscador;
import br.upe.analisealgoritmos.utils.CSVExporter;
import br.upe.analisealgoritmos.utils.GeradorDados;

public class ExperimentoBuscaGrafico {

    public static void executar() {

        int[] tamanhos = {100, 1000, 5000};
        List<String[]> resultados = new ArrayList<>();

        Buscador buscaLinear = new BuscaLinear();
        Buscador buscaBinaria = new BuscaBinaria();

        for (int n : tamanhos) {

            int[] vetor = GeradorDados.gerarVetorAleatorio(n);
            int alvo = vetor[n / 2];

            /*
             * BUSCA LINEAR
             */
            long inicio = System.nanoTime();
            buscaLinear.buscar(vetor, alvo);
            long fim = System.nanoTime();

            resultados.add(new String[]{
                    String.valueOf(n),
                    "busca",
                    "BuscaLinear",
                    String.valueOf(fim - inicio)
            });

            /*
             * BUSCA BINÁRIA (requer vetor ordenado)
             */
            Arrays.sort(vetor);

            inicio = System.nanoTime();
            buscaBinaria.buscar(vetor, alvo);
            fim = System.nanoTime();

            resultados.add(new String[]{
                    String.valueOf(n),
                    "busca",
                    "BuscaBinaria",
                    String.valueOf(fim - inicio)
            });
        }

        /*
         * ============================================================
         * EXPORTAÇÃO
         * ============================================================
         */
        CSVExporter.salvar("resultados/busca.csv", resultados);

        // 🔥 ESSENCIAL — histórico
        CSVExporter.salvarComHistorico("resultados", "busca", resultados);

        System.out.println("✅ Experimento de busca finalizado.");
    }
}