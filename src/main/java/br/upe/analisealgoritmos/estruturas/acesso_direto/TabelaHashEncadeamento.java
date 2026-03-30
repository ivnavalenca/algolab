package br.upe.analisealgoritmos.estruturas.acesso_direto;

import br.upe.analisealgoritmos.estruturas.lineares.ListaEncadeada;

/*
 * CLASSE: TabelaHashEncadeamento
 *
 * Implementação de tabela hash com encadeamento.
 *
 * Ideia:
 * - Cada posição da tabela contém uma lista encadeada
 * - Colisões são resolvidas por encadeamento
 *
 * Complexidade:
 * - Inserção: O(1) médio
 * - Busca: O(1) médio
 * - Pior caso: O(n)
 */

public class TabelaHashEncadeamento {

    private ListaEncadeada[] tabela;
    private int capacidade;

    /*
     * Construtor
     */
    public TabelaHashEncadeamento(int capacidade) {

        this.capacidade = capacidade;

        tabela = new ListaEncadeada[capacidade];

        /*
         * Inicializa cada posição com uma lista
         */
        for (int i = 0; i < capacidade; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }

    /*
     * Função hash
     */
    private int hash(int chave) {
        return chave % capacidade;
    }

    /*
     * Inserção
     * O(1) médio
     */
    public void inserir(int valor) {

        int indice = hash(valor);

        tabela[indice].inserir(valor);
    }

    /*
     * Busca
     * O(1) médio
     */
    public boolean buscar(int valor) {

        int indice = hash(valor);

        return tabela[indice].buscar(valor);
    }

    /*
     * Remoção
     * O(1) médio
     */
    public void remover(int valor) {

        int indice = hash(valor);

        tabela[indice].remover(valor);
    }

    /*
     * Exibir tabela
     */
    public void imprimir() {

        for (int i = 0; i < capacidade; i++) {

            System.out.print(i + ": ");

            // aqui seria ideal imprimir a lista (simplificado)
            System.out.println("[lista]");
        }
    }

    /*
     * Exemplo de uso
     */
    public static void main(String[] args) {

        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(10);

        tabela.inserir(15);
        tabela.inserir(25);
        tabela.inserir(35);

        System.out.println("Busca 25: " + tabela.buscar(25));
        System.out.println("Busca 99: " + tabela.buscar(99));
    }
}