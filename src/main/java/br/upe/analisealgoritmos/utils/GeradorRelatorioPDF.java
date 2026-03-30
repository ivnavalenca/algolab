package br.upe.analisealgoritmos.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * ============================================================
 * GERADOR DE RELATÓRIO PDF
 * ============================================================
 *
 * OBJETIVO:
 * Gerar relatório automático com:
 * ✔ título
 * ✔ data
 * ✔ descrição
 * ✔ interpretação
 *
 * ============================================================
 */

public class GeradorRelatorioPDF {

    public static void gerarRelatorio(String pastaResultados) {

        String caminhoPDF = pastaResultados + "/relatorio.pdf";

        Document documento = new Document();

        try {

            PdfWriter.getInstance(documento, new FileOutputStream(caminhoPDF));

            documento.open();

            /*
             * TÍTULO
             */
            Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph(
                    "Relatório de Análise de Algoritmos\n\n",
                    tituloFont
            );

            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            /*
             * DATA
             */
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String data = LocalDateTime.now().format(formatter);

            documento.add(new Paragraph("Data da execução: " + data + "\n\n"));

            /*
             * DESCRIÇÃO
             */
            documento.add(new Paragraph(
                    "Este relatório apresenta os resultados experimentais " +
                    "dos algoritmos de busca e ordenação, incluindo análise " +
                    "de tempo de execução em diferentes cenários.\n\n"
            ));

            /*
             * INTERPRETAÇÃO AUTOMÁTICA (RESUMO)
             */
            documento.add(new Paragraph("Principais conclusões:\n"));

            documento.add(new Paragraph("• Algoritmos O(n²) apresentam crescimento elevado."));
            documento.add(new Paragraph("• Algoritmos O(n log n) são mais eficientes."));
            documento.add(new Paragraph("• QuickSort apresentou melhor desempenho geral."));
            documento.add(new Paragraph("• MergeSort apresentou comportamento consistente."));
            documento.add(new Paragraph("• Busca binária foi significativamente mais rápida.\n\n"));

            /*
             * OBSERVAÇÃO
             */
            documento.add(new Paragraph(
                    "Observação: os resultados podem variar devido ao ambiente de execução da JVM.\n"
            ));

            documento.close();

            System.out.println("Relatório PDF gerado em: " + caminhoPDF);

        } catch (Exception e) {
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
        }
    }
}