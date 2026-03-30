/*
 * CLASSE: TabelaHashDoubleHashing
 *
 * Usa duas funções hash para reduzir colisões.
 */

public class TabelaHashDoubleHashing {

    private Integer[] tabela;

    public TabelaHashDoubleHashing(int capacidade) {
        tabela = new Integer[capacidade];
    }

    private int hash1(int chave) {
        return chave % tabela.length;
    }

    private int hash2(int chave) {
        return 7 - (chave % 7);
    }

    public void inserir(int valor) {

        int i = hash1(valor);
        int step = hash2(valor);

        while (tabela[i] != null) {
            i = (i + step) % tabela.length;
        }

        tabela[i] = valor;
    }
}