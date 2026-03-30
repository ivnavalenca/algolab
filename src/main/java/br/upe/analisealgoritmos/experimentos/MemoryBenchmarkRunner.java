package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * MEMORY BENCHMARK RUNNER
 * ============================================================
 *
 * OBJETIVO:
 * Medir o consumo de memória de algoritmos.
 *
 * ============================================================
 * IMPORTANTE:
 *
 * Medir memória na JVM não é exato, mas fornece
 * uma boa estimativa comparativa.
 *
 * ============================================================
 * USO:
 *
 * long memoria = medirMemoria(() -> algoritmo.executar());
 *
 * ============================================================
 */

public class MemoryBenchmarkRunner {

    /*
     * Mede memória usada por um algoritmo
     */
    public static long medirMemoria(Runnable algoritmo) {

        Runtime runtime = Runtime.getRuntime();

        /*
         * Força limpeza de memória (GC)
         */
        System.gc();

        /*
         * Memória antes
         */
        long antes = runtime.totalMemory() - runtime.freeMemory();

        /*
         * Executa algoritmo
         */
        algoritmo.run();

        /*
         * Força limpeza novamente
         */
        System.gc();

        /*
         * Memória depois
         */
        long depois = runtime.totalMemory() - runtime.freeMemory();

        /*
         * Diferença (consumo)
         */
        return depois - antes;
    }
}