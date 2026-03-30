package br.upe.analisealgoritmos.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/*
 * ============================================================
 * CLASSE: GerenciadorResultados
 * ============================================================
 *
 * OBJETIVO:
 * Gerenciar arquivos de saída do sistema:
 * ✔ CSV
 * ✔ gráficos
 * ✔ relatórios PDF
 *
 * FUNCIONALIDADES:
 * ✔ Criar nomes únicos com timestamp
 * ✔ Organizar pasta de resultados
 * ✔ Recuperar último CSV gerado
 *
 * ============================================================
 */

public class GerenciadorResultados {

    private static final String PASTA_RESULTADOS = "resultados";

    /*
     * ============================================================
     * GARANTE QUE A PASTA EXISTE
     * ============================================================
     */
    private static void garantirPasta() {

        File pasta = new File(PASTA_RESULTADOS);

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    /*
     * ============================================================
     * GERA CAMINHO DE ARQUIVO COM TIMESTAMP
     * ============================================================
     */
    public static String caminhoArquivo(String nomeBase) {

        garantirPasta();

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        return PASTA_RESULTADOS + "/" + nomeBase + "_" + timestamp;
    }

    /*
     * ============================================================
     * GERA CAMINHO COMPLETO COM EXTENSÃO
     * ============================================================
     */
    public static String caminhoArquivo(String nomeBase, String extensao) {

        return caminhoArquivo(nomeBase) + "." + extensao;
    }

    /*
     * ============================================================
     * OBTÉM O ÚLTIMO CSV GERADO
     * ============================================================
     */
    public static String getUltimoCSV() {

        File pasta = new File(PASTA_RESULTADOS);

        if (!pasta.exists()) {
            throw new RuntimeException("❌ Pasta de resultados não encontrada.");
        }

        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".csv"));

        if (arquivos == null || arquivos.length == 0) {
            throw new RuntimeException("❌ Nenhum CSV encontrado.");
        }

        // Ordena pelo mais recente
        Arrays.sort(arquivos, Comparator.comparingLong(File::lastModified).reversed());

        return arquivos[0].getAbsolutePath();
    }
}