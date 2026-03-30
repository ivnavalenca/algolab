/*
 * CLASSE: TabelaHashLinearProbing
 *
 * Tratamento de colisão por tentativa linear.
 */

public class TabelaHashLinearProbing {

    private Integer[] tabela;

    public TabelaHashLinearProbing(int capacidade) {
        tabela = new Integer[capacidade];
    }

    private int hash(int chave) {
        return chave % tabela.length;
    }

    /*
     * INSERÇÃO
     *
     * Complexidade:
     * - O(1) médio
     * - O(n) pior caso
     */
    public void inserir(int valor) {

        int i = hash(valor);

        while (tabela[i] != null) {
            i = (i + 1) % tabela.length;
        }

        tabela[i] = valor;
    }

    /*
     * BUSCA
     *
     * Complexidade:
     * - O(1) médio
     * - O(n) pior caso
     */
    public boolean buscar(int valor) {

        int i = hash(valor);

        while (tabela[i] != null) {

            if (tabela[i] == valor) {
                return true;
            }

            i = (i + 1) % tabela.length;
        }

        return false;
    }
}