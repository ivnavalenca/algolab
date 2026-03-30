package br.upe.analisealgoritmos.ordenacao;

public class BubbleSort implements Ordenador {

    public void ordenar(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = 0; j < v.length - i - 1; j++) {
                if (v[j] > v[j + 1]) {
                    int tmp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = tmp;
                }
            }
        }
    }

    public String nome() {
        return "BubbleSort";
    }
}