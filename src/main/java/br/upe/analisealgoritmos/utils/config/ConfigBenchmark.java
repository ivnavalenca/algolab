package br.upe.analisealgoritmos.utils.config;

/*
 * ============================================================
 * CLASSE: ConfigBenchmark
 * ============================================================
 *
 * OBJETIVO:
 * Centralizar configurações utilizadas nos experimentos.
 *
 * BENEFÍCIOS:
 * ✔ Evita valores hardcoded
 * ✔ Facilita ajustes globais
 * ✔ Padroniza experimentos
 *
 * ============================================================
 */

public class ConfigBenchmark {

    /*
     * ============================================================
     * TAMANHOS PADRÃO DE ENTRADA
     * ============================================================
     */
    public static final int[] TAMANHOS_PADRAO = {
            100, 500, 1000, 2000, 5000
    };

    /*
     * ============================================================
     * NÚMERO DE EXECUÇÕES (REPETIÇÕES)
     * ============================================================
     */
    public static final int REPETICOES = 5;

    /*
     * ============================================================
     * DIRETÓRIOS
     * ============================================================
     */
    public static final String DIR_RESULTADOS = "resultados";
    public static final String DIR_GRAFICOS = "graficos";
    public static final String DIR_DOCS = "docs";

    /*
     * ============================================================
     * NOME DO ARQUIVO PRINCIPAL
     * ============================================================
     */
    public static final String ARQUIVO_LATEST = "latest.csv";

    /*
     * ============================================================
     * CONSTRUTOR PRIVADO (CLASSE UTILITÁRIA)
     * ============================================================
     */
    private ConfigBenchmark() {
        // evita instanciação
    }
}