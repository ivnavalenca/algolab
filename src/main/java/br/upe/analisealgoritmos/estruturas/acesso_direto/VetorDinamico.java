package br.upe.analisealgoritmos.estruturas.acesso_direto;

/*
 * Estrutura baseada em array que cresce automaticamente.
 *
 * PRINCIPAL IDEIA:
 * - Dobrar o tamanho quando necessário
 */

public class VetorDinamico {

    private int[] dados = new int[5];
    private int tamanho = 0;

    /*
     * INSERÇÃO
     * Complexidade:
     * - O(1) amortizado
     * - O(n) quando redimensiona
     */
    public void adicionar(int valor) {

        if (tamanho == dados.length) {
            redimensionar();
        }

        dados[tamanho++] = valor;
    }

    /*
     * REDIMENSIONAMENTO
     */
    private void redimensionar() {

        int[] novo = new int[dados.length * 2];

        for (int i = 0; i < dados.length; i++) {
            novo[i] = dados[i];
        }

        dados = novo;
    }
}