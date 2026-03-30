package br.upe.analisealgoritmos.experimentos;

import br.upe.analisealgoritmos.ordenacao.*;
import br.upe.analisealgoritmos.utils.GeradorVetor;

/*
 * ============================================================
 * EXPERIMENTO DE ORDENAÇÃO POR CASOS (VERSÃO PROFISSIONAL)
 * ============================================================
 *
 * Analisa:
 * ✔ Melhor caso (vetor ordenado)
 * ✔ Caso médio (vetor aleatório)
 * ✔ Pior caso (vetor invertido)
 *
 * ============================================================
 */

public class ExperimentoOrdenacaoCasosGrafico {

    private static final int EXECUCOES = 10;

    public static void executar() {

        int[] tamanhos = {1000, 2000, 5000, 10000};

        TipoOrdenacao[] algoritmos = {
                TipoOrdenacao.BUBBLE,
                TipoOrdenacao.INSERTION,
                TipoOrdenacao.SELECTION,
                TipoOrdenacao.MERGE,
                TipoOrdenacao.QUICK
        };

        BenchmarkRunner runner = new BenchmarkRunner(EXECUCOES);

        System.out.println("=== ORDENAÇÃO POR CASOS ===");

        for (int n : tamanhos) {

            System.out.println("\nTamanho: " + n);

            int[] melhorCaso = GeradorVetor.gerarOrdenado(n);
            int[] casoMedio = GeradorVetor.gerarAleatorio(n);
            int[] piorCaso = GeradorVetor.gerarInvertido(n);

            for (TipoOrdenacao tipo : algoritmos) {

                Ordenador algoritmo = FabricaOrdenadores.criar(tipo);

                long tempoMelhor = runner.executarBenchmark(algoritmo, melhorCaso);
                long tempoMedio = runner.executarBenchmark(algoritmo, casoMedio);
                long tempoPior = runner.executarBenchmark(algoritmo, piorCaso);

                System.out.println(
                        algoritmo.getNome()
                        + " → Melhor: " + tempoMelhor + " ns"
                        + " | Médio: " + tempoMedio + " ns"
                        + " | Pior: " + tempoPior + " ns"
                );
            }
        }
    }
}