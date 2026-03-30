package br.upe.analisealgoritmos.estruturas.acesso_direto;

/*
 * ============================================================
 * CLASSE: TabelaHashLinearProbing
 * ============================================================
 */

public class TabelaHashLinearProbing {

    private Integer[] tabela;

    public TabelaHashLinearProbing(int tamanho) {
        tabela = new Integer[tamanho];
    }

    /*
     * ============================================================
     * INSERIR
     * ============================================================
     */
    public void inserir(int valor) {

        int i = hash(valor);

        while (tabela[i] != null) {
            i = (i + 1) % tabela.length;
        }

        tabela[i] = valor;
    }

    private int hash(int valor) {
        return valor % tabela.length;
    }
}