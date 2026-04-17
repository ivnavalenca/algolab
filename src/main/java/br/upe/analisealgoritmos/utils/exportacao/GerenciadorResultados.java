package br.upe.analisealgoritmos.utils.exportacao;

/*
 * ============================================================
 * CLASSE: GerenciadorResultados
 * ============================================================
 *
 * OBJETIVO:
 * Gerenciar acesso a arquivos de resultados.
 *
 * ============================================================
 */

import java.io.File;

public class GerenciadorResultados {

    private static final String BASE = "resultados";

    public static File getLatest() {
        return new File(BASE + "/latest.csv");
    }

    public static File getHistorico() {
        return new File(BASE + "/historico");
    }

    public static boolean existeLatest() {
        return getLatest().exists();
    }
}