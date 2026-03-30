package br.upe.analisealgoritmos.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * ============================================================
 * GERENCIADOR DE RESULTADOS
 * ============================================================
 *
 * OBJETIVO:
 * Organizar automaticamente os resultados dos experimentos
 * em pastas únicas por execução.
 *
 * ============================================================
 * ESTRUTURA GERADA:
 *
 * resultados/
 *   └── run_2026-03-29_15-30-10/
 *         ├── ordenacao.csv
 *         ├── ordenacao_grafico.png
 *         ├── busca.csv
 *
 * ============================================================
 * BENEFÍCIOS:
 *
 * ✔ Evita sobrescrever arquivos
 * ✔ Permite histórico de execuções
 * ✔ Organização profissional
 *
 * ============================================================
 */

public class GerenciadorResultados {

    private static String pastaAtual;

    /*
     * Cria pasta única baseada em data/hora
     */
    public static String criarPastaExecucao() {

        if (pastaAtual != null) {
            return pastaAtual;
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        String timestamp = LocalDateTime.now().format(formatter);

        pastaAtual = "resultados/run_" + timestamp;

        File pasta = new File(pastaAtual);

        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();

            if (!criada) {
                System.err.println("Erro ao criar pasta de resultados!");
            }
        }

        System.out.println("Pasta de resultados: " + pastaAtual);

        return pastaAtual;
    }

    /*
     * Retorna caminho completo de um arquivo dentro da pasta
     */
    public static String caminhoArquivo(String nomeArquivo) {

        if (pastaAtual == null) {
            criarPastaExecucao();
        }

        return pastaAtual + "/" + nomeArquivo;
    }
}