package br.upe.analisealgoritmos.ordenacao;

public class QuickSort implements Ordenador {

    public void ordenar(int[] v) {
        quick(v, 0, v.length - 1);
    }

    private void quick(int[] v, int l, int r) {
        if (l >= r) return;

        int p = v[r];
        int i = l;

        for (int j = l; j < r; j++) {
            if (v[j] <= p) {
                int tmp = v[i];
                v[i] = v[j];
                v[j] = tmp;
                i++;
            }
        }

        int tmp = v[i];
        v[i] = v[r];
        v[r] = tmp;

        quick(v, l, i - 1);
        quick(v, i + 1, r);
    }

    public String nome() {
        return "QuickSort";
    }
}