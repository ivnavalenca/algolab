package br.upe.analisealgoritmos.ordenacao;

public class MergeSort implements Ordenador {

    public void ordenar(int[] v) {
        mergeSort(v, 0, v.length - 1);
    }

    private void mergeSort(int[] v, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;

        mergeSort(v, l, m);
        mergeSort(v, m + 1, r);

        merge(v, l, m, r);
    }

    private void merge(int[] v, int l, int m, int r) {
        int[] temp = new int[v.length];

        int i = l, j = m + 1, k = l;

        while (i <= m && j <= r) {
            if (v[i] <= v[j]) temp[k++] = v[i++];
            else temp[k++] = v[j++];
        }

        while (i <= m) temp[k++] = v[i++];
        while (j <= r) temp[k++] = v[j++];

        for (i = l; i <= r; i++) {
            v[i] = temp[i];
        }
    }

    public String nome() {
        return "MergeSort";
    }
}