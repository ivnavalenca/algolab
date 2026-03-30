package br.upe.analisealgoritmos.experimentos;

import br.upe.analisealgoritmos.busca.*;
import br.upe.analisealgoritmos.utils.*;

import java.util.Arrays;

/*
 * ============================================================
 * EXPERIMENTO DE BUSCA (VERSÃO FINAL CORRIGIDA)
 * ============================================================
 *
 * ✔ compatível com BenchmarkRunner novo
 * ✔ usa Runnable corretamente
 * ✔ mantém CSV + gráfico
 *
 * ============================================================
 */

public class ExperimentoBuscaGrafico {

    private static final int EXECUCOES = 20;

    public static void executar() {

        /*
         * Pasta da execução
         */
        String pasta = GerenciadorResultados.criarPastaExecucao();

        String caminhoCSV = GerenciadorResultados.caminhoArquivo("busca.csv");
        String caminhoGrafico = GerenciadorResultados.caminhoArquivo("busca_grafico.png");

        int[] tamanhos = {1000, 2000, 4000, 8000, 16000};

        BenchmarkRunner runner = new BenchmarkRunner(EXECUCOES);

        /*
         * Cria CSV
         */
        CSVExporter.criarArquivo(
                caminhoCSV,
                "n,Linear,Binaria"
        );

        System.out.println("\n=== BUSCA (VERSÃO FINAL) ===");

        for (int n : tamanhos) {

            int[] vetor = GeradorVetor.gerarAleatorio(n);

            /*
             * Escolhe valor (último → pior caso para linear)
             */
            int chave = vetor[n - 1];

            /*
             * Busca Linear
             */
            Buscador buscaLinear = FabricaBuscadores.criar(TipoBusca.LINEAR);

            long tempoLinear = runner.medirTempo(
                    () -> buscaLinear.buscar(vetor, chave)
            );

            /*
             * Busca Binária (vetor ordenado)
             */
            int[] vetorOrdenado = vetor.clone();
            Arrays.sort(vetorOrdenado);

            Buscador buscaBinaria = FabricaBuscadores.criar(TipoBusca.BINARIA);

            long tempoBinaria = runner.medirTempo(
                    () -> buscaBinaria.buscar(vetorOrdenado, chave)
            );

            /*
             * Log formatado
             */
            System.out.printf(
                    "n=%-6d | Linear: %-10d | Binária: %-10d%n",
                    n, tempoLinear, tempoBinaria
            );

            /*
             * Salva CSV
             */
            CSVExporter.adicionarLinha(
                    caminhoCSV,
                    n + "," + tempoLinear + "," + tempoBinaria
            );
        }

        System.out.println("\nCSV salvo em: " + caminhoCSV);

        /*
         * Gera gráfico
         */
        GeradorGraficoCSV.gerarGrafico(
                caminhoCSV,
                caminhoGrafico
        );

        System.out.println("Gráfico salvo em: " + caminhoGrafico);
    }
}