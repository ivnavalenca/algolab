package br.upe.analisealgoritmos.utils.exportacao;

/*
 * ============================================================
 * CLASSE: GeradorRelatorioPDF
 * ============================================================
 *
 * OBJETIVO:
 * Gerar relatório simples em PDF (placeholder).
 *
 * OBS:
 * Pode ser evoluído com biblioteca externa (iText, etc.)
 *
 * ============================================================
 */

import java.io.FileWriter;

public class GeradorRelatorioPDF {

    public static void gerar(String caminho, String conteudo) {

        try {

            FileWriter fw = new FileWriter(caminho);
            fw.write(conteudo);
            fw.close();

            System.out.println("📄 Relatório gerado: " + caminho);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}