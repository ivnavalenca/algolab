package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * CLASSE: FabricaBuscadores
 * ============================================================
 *
 * OBJETIVO:
 * Criar instâncias de algoritmos de busca dinamicamente.
 *
 * PADRÃO:
 * Factory Pattern
 *
 * ============================================================
 */

public class FabricaBuscadores {

    /*
     * ============================================================
     * MÉTODO DE CRIAÇÃO
     * ============================================================
     */
    public static Buscador criar(String tipo) {

        switch (tipo.toLowerCase()) {

            case "linear":
                return new BuscaLinear();

            case "binaria":
                return new BuscaBinaria();

            default:
                throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
    }
}