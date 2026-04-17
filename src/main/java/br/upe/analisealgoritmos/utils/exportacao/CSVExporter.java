package br.upe.analisealgoritmos.utils.exportacao;

/*
 * ============================================================
 * CLASSE: CSVExporter
 * ============================================================
 *
 * OBJETIVO:
 * Exportar resultados de experimentos para CSV.
 *
 * FUNCIONALIDADES:
 * ✔ Gerar latest.csv (entrada da pipeline Python)
 * ✔ Manter histórico automático
 *
 * FORMATO:
 * tamanho,cenario,algoritmo,tempo
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
     * EXPORTAÇÃO PADRÃO DA PIPELINE
     * ============================================================
     */
    public static void salvarPipeline(List<String[]> dados) {

        try {

            String basePath = "resultados";

            /*
             * ========================================================
             * GARANTE DIRETÓRIOS
             * ========================================================
             */
            new File(basePath).mkdirs();
            new File(basePath + "/historico").mkdirs();

            /*
             * ========================================================
             * SALVA latest.csv
             * ========================================================
             */
            salvarCSV(basePath + "/latest.csv", dados);

            /*
             * ========================================================
             * SALVA HISTÓRICO
             * ========================================================
             */
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            String caminhoHistorico =
                    basePath + "/historico/run_" + timestamp + ".csv";

            salvarCSV(caminhoHistorico, dados);

            System.out.println("📁 latest.csv atualizado");
            System.out.println("📁 Histórico salvo: " + caminhoHistorico);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * ============================================================
     * SALVAR CSV GENÉRICO
     * ============================================================
     */
    private static void salvarCSV(String caminho, List<String[]> dados) throws Exception {

        File arquivo = new File(caminho);
        arquivo.getParentFile().mkdirs();

        PrintWriter pw = new PrintWriter(arquivo);

        pw.println("tamanho,cenario,algoritmo,tempo");

        for (String[] linha : dados) {
            pw.println(String.join(",", linha));
        }

        pw.close();
    }
}