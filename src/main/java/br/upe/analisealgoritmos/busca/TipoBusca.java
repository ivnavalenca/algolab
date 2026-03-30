package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * ENUM TipoBusca
 * ============================================================
 *
 * OBJETIVO:
 * Representar todos os algoritmos de busca disponíveis.
 *
 * ============================================================
 * BENEFÍCIOS:
 *
 * ✔ Evita uso de strings soltas
 * ✔ Facilita manutenção
 * ✔ Permite uso com fábrica
 * ✔ Código mais legível
 *
 * ============================================================
 */

public enum TipoBusca {

    LINEAR,
    BINARIA;

    /*
     * Nome formatado (para exibição)
     */
    public String getNomeFormatado() {
        switch (this) {
            case LINEAR:
                return "Busca Linear";
            case BINARIA:
                return "Busca Binária";
            default:
                return "Desconhecido";
        }
    }
}