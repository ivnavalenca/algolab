package br.upe.analisealgoritmos.utils;

/*
 * ============================================================
 * CLASSE: CSVExporter
 * ============================================================
 *
 * OBJETIVO:
 * Exportar dados para CSV e manter histórico automaticamente.
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
     * SALVAR CSV PADRÃO
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
            e.printStackTrace();
        }
    }

    /*
     * ============================================================
     * SALVAR HISTÓRICO (ROBUSTO)
     * ============================================================
     */
    public static void salvarComHistorico(String basePath, String nomeArquivo, List<String[]> dados) {

        try {

            // 🔥 garante pasta SEMPRE
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
            e.printStackTrace();
        }
    }
}