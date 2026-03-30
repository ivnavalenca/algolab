package br.upe.analisealgoritmos.estruturas.lineares;

/*
 * ============================================================
 * CLASSE: VetorDinamico
 * ============================================================
 *
 * OBJETIVO:
 * Estrutura de vetor com crescimento dinâmico.
 *
 * ============================================================
 */

public class VetorDinamico {

    private int[] dados;
    private int tamanho;

    public VetorDinamico() {
        dados = new int[10];
        tamanho = 0;
    }

    /*
     * ============================================================
     * ADICIONAR ELEMENTO
     * ============================================================
     */
    public void adicionar(int valor) {

        if (tamanho == dados.length) {
            redimensionar();
        }

        dados[tamanho++] = valor;
    }

    /*
     * ============================================================
     * REDIMENSIONAMENTO
     * ============================================================
     */
    private void redimensionar() {

        int[] novo = new int[dados.length * 2];

        for (int i = 0; i < dados.length; i++) {
            novo[i] = dados[i];
        }

        dados = novo;
    }
}