package br.upe.analisealgoritmos;

import br.upe.analisealgoritmos.ordenacao.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesteOrdenacao {

    private final int[] vetor = {5, 3, 8, 1, 2};

    @Test
    void testBubbleSort() {
        testarOrdenacao(new BubbleSort());
    }

    @Test
    void testMergeSort() {
        testarOrdenacao(new MergeSort());
    }

    @Test
    void testQuickSort() {
        testarOrdenacao(new QuickSort());
    }

    private void testarOrdenacao(Ordenador algoritmo) {

        int[] copia = vetor.clone();

        algoritmo.ordenar(copia);

        for (int i = 0; i < copia.length - 1; i++) {
            assertTrue(copia[i] <= copia[i + 1]);
        }
    }
}