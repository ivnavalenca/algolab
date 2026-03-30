package br.upe.analisealgoritmos.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * ============================================================
 * EXPORTADOR CSV (VERSÃO PROFISSIONAL)
 * ============================================================
 *
 * MELHORIAS:
 * ✔ cria arquivo com cabeçalho
 * ✔ adiciona linhas com segurança
 * ✔ evita sobrescrever sem querer
 * ✔ tratamento de erro
 *
 * ============================================================
 */

public class CSVExporter {

    /*
     * ============================================================
     * CRIA ARQUIVO COM CABEÇALHO
     * ============================================================
     */
    public static void criarArquivo(String caminho, String cabecalho) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {

            writer.write(cabecalho);
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Erro ao criar CSV: " + e.getMessage());
        }
    }

    /*
     * ============================================================
     * ADICIONA LINHA
     * ============================================================
     */
    public static void adicionarLinha(String caminho, String linha) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {

            writer.write(linha);
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Erro ao escrever CSV: " + e.getMessage());
        }
    }
}