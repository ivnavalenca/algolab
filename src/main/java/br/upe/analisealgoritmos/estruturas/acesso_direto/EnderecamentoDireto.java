/*
 * CLASSE: EnderecamentoDireto
 *
 * Implementação simples de acesso direto.
 *
 * A chave é o índice.
 */

public class EnderecamentoDireto {

    private int[] tabela = new int[100];

    /*
     * INSERÇÃO
     *
     * Complexidade: O(1)
     */
    public void inserir(int chave, int valor) {
        tabela[chave] = valor;
    }

    /*
     * BUSCA
     *
     * Complexidade: O(1)
     */
    public int buscar(int chave) {
        return tabela[chave];
    }

    /*
     * REMOÇÃO
     *
     * Complexidade: O(1)
     */
    public void remover(int chave) {
        tabela[chave] = 0;
    }
}