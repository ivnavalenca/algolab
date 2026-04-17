package br.upe.analisealgoritmos.utils;

/*
 * ============================================================
 * CLASSE: CSVExporter
 * ============================================================
 *
 * OBJETIVO:
 * Exportar resultados de benchmarks para CSV de forma padronizada
 * e integrada à pipeline Python.
 *
 * FUNCIONALIDADES:
 * ✔ Gera arquivo latest.csv (entrada da pipeline)
 * ✔ Mantém histórico versionado automaticamente
 * ✔ Cria diretórios se necessário
 *
 * ESTRUTURA DO CSV:
 * tamanho,cenario,algoritmo,tempo
 *
 * SAÍDAS:
 * resultados/latest.csv
 * resultados/historico/run_<timestamp>.csv
 *
 * ============================================================
 */

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CSVExporter {

    /*
     * ============================================================
     * EXPORTAÇÃO PRINCIPAL (PIPELINE)
     * ============================================================
     *
     * Gera:
     * ✔ latest.csv
     * ✔ histórico automaticamente
     *
     * ============================================================
     */
    public static void salvarPipeline(List<String[]> dados) {

        String basePath = "resultados";

        salvarLatest(basePath + "/latest.csv", dados);
        salvarHistorico(basePath, "run", dados);
    }

    /*
     * ============================================================
     * SALVAR LATEST (PADRÃO DA PIPELINE)
     * ============================================================
     */
    public static void salvarLatest(String caminho, List<String[]> dados) {

        try {

            File arquivo = new File(caminho);
            arquivo.getParentFile().mkdirs();

            PrintWriter pw = new PrintWriter(arquivo);

            // Cabeçalho padrão
            pw.println("tamanho,cenario,algoritmo,tempo");

            for (String[] linha : dados) {
                pw.println(String.join(",", linha));
            }

            pw.close();

            System.out.println("📄 latest.csv atualizado em: " + caminho);

        } catch (Exception e) {
            System.err.println("Erro ao salvar latest.csv:");
            e.printStackTrace();
        }
    }

    /*
     * ============================================================
     * SALVAR HISTÓRICO (VERSIONAMENTO)
     * ============================================================
     */
    public static void salvarHistorico(String basePath, String nomeArquivo, List<String[]> dados) {

        try {

            File pasta = new File(basePath + "/historico");
            pasta.mkdirs();

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            String caminho = basePath + "/historico/" + nomeArquivo + "_" + timestamp + ".csv";

            PrintWriter pw = new PrintWriter(new File(caminho));

            pw.println("tamanho,cenario,algoritmo,tempo");

            for (String[] linha : dados) {
                pw.println(String.join(",", linha));
            }

            pw.close();

            System.out.println("📁 Histórico salvo em: " + caminho);

        } catch (Exception e) {
            System.err.println("Erro ao salvar histórico:");
            e.printStackTrace();
        }
    }

    /*
     * ============================================================
     * MODO SIMPLES (OPCIONAL)
     * ============================================================
     *
     * Apenas salva CSV sem histórico
     *
     * ============================================================
     */
    public static void salvar(String caminho, List<String[]> dados) {

        try {

            File arquivo = new File(caminho);
            arquivo.getParentFile().mkdirs();

            PrintWriter pw = new PrintWriter(arquivo);

            pw.println("tamanho,cenario,algoritmo,tempo");

            for (String[] linha : dados) {
                pw.println(String.join(",", linha));
            }

            pw.close();

            System.out.println("📁 CSV salvo em: " + caminho);

        } catch (Exception e) {
            System.err.println("Erro ao salvar CSV:");
            e.printStackTrace();
        }
    }
}