package br.upe.analisealgoritmos.experimentos.runners;

/*
 * ============================================================
 * CLASSE: MemoryBenchmarkRunner
 * ============================================================
 *
 * OBJETIVO:
 * Medir consumo de memória em estruturas de dados.
 *
 * PADRÃO:
 * ✔ Implementa interface Experimento
 * ✔ Integra com BenchmarkRunner
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;
import br.upe.analisealgoritmos.utils.dados.GeradorDados;

public class MemoryBenchmarkRunner implements Experimento {

    @Override
    public String getNome() {
        return "Memoria";
    }

    @Override
    public List<String[]> executar() {

        System.out.println("🧠 Executando benchmark de memória");

        List<String[]> resultados = new ArrayList<>();

        Runtime runtime = Runtime.getRuntime();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            int[] dados = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ALEATORIO);

            /*
             * ========================================================
             * FORÇA LIMPEZA DE MEMÓRIA
             * ========================================================
             */
            runtime.gc();

            long antes = runtime.totalMemory() - runtime.freeMemory();

            /*
             * ========================================================
             * OPERAÇÃO TESTADA
             * ========================================================
             */
            int soma = 0;
            for (int v : dados) soma += v;

            long depois = runtime.totalMemory() - runtime.freeMemory();

            long memoria = depois - antes;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "memoria",
                    "Array",
                    String.valueOf(memoria)
            });
        }

        return resultados;
    }
}