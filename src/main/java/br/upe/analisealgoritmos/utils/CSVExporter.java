package br.upe.analisealgoritmos.utils;

/*
 * ============================================================
 * CLASSE: CSVExporter
 * ============================================================
 *
 * OBJETIVO:
 * Exportar resultados para arquivos CSV.
 *
 * FORMATO:
 * tamanho,cenario,algoritmo,tempo
 *
 * ============================================================
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class CSVExporter {

    /*
     * ============================================================
     * SALVAR CSV
     * ============================================================
     */
    public static void salvar(String caminho, List<String[]> dados) {

        try {

            /*
             * ============================================================
             * GARANTIR PASTA DE SAÍDA
             * ============================================================
             */
            File arquivo = new File(caminho);
            File pasta = arquivo.getParentFile();

            if (pasta != null && !pasta.exists()) {
                pasta.mkdirs();
            }

            /*
             * ============================================================
             * ESCRITA DO CSV
             * ============================================================
             */
            PrintWriter pw = new PrintWriter(arquivo);

            // Cabeçalho
            pw.println("tamanho,cenario,algoritmo,tempo");

            for (String[] linha : dados) {
                pw.println(String.join(",", linha));
            }

            pw.close();

            System.out.println("📁 CSV salvo em: " + caminho);

        } catch (Exception e) {
            System.err.println("❌ Erro ao salvar CSV:");
            e.printStackTrace();
        }
    }
}