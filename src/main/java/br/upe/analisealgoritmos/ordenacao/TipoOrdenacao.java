package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * ENUM TipoOrdenacao
 * ============================================================
 *
 * IDEIA:
 * Representa todos os algoritmos disponíveis no sistema.
 *
 * Isso permite:
 * ✔ selecionar algoritmos dinamicamente
 * ✔ evitar uso de strings soltas
 * ✔ melhorar legibilidade e manutenção
 *
 * ============================================================
 */

public enum TipoOrdenacao {

    BUBBLE,
    INSERTION,
    SELECTION,
    MERGE,
    QUICK;

    /*
     * Retorna o nome formatado (para exibição)
     */
    public String getNomeFormatado() {
        switch (this) {
            case BUBBLE: return "BubbleSort";
            case INSERTION: return "InsertionSort";
            case SELECTION: return "SelectionSort";
            case MERGE: return "MergeSort";
            case QUICK: return "QuickSort";
            default: return "Desconhecido";
        }
    }
}