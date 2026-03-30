package br.upe.analisealgoritmos.ordenacao;

public class SelectionSort implements Ordenador {

    public void ordenar(int[] v) {
        for (int i = 0; i < v.length; i++) {
            int min = i;

            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[min]) {
                    min = j;
                }
            }

            int tmp = v[i];
            v[i] = v[min];
            v[min] = tmp;
        }
    }

    public String nome() {
        return "SelectionSort";
    }
}