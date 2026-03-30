package br.upe.analisealgoritmos.ordenacao;

/*
 * ============================================================
 * FÁBRICA DE ORDENADORES (Factory Pattern)
 * ============================================================
 *
 * IDEIA:
 * Centraliza a criação dos algoritmos de ordenação.
 *
 * Em vez de instanciar diretamente:
 *   new BubbleSort()
 *
 * usamos:
 *   FabricaOrdenadores.criar(TipoOrdenacao.BUBBLE)
 *
 * ============================================================
 * BENEFÍCIOS:
 *
 * ✔ Código desacoplado
 * ✔ Fácil de expandir (adicionar novos algoritmos)
 * ✔ Evita duplicação de lógica
 * ✔ Mais limpo e profissional
 *
 * ============================================================
 */

public class FabricaOrdenadores {

    /*
     * Método responsável por criar o algoritmo desejado
     */
    public static Ordenador criar(TipoOrdenacao tipo) {

        switch (tipo) {

            case BUBBLE:
                return new BubbleSort();

            case INSERTION:
                return new InsertionSort();

            case SELECTION:
                return new SelectionSort();

            case MERGE:
                return new MergeSort();

            case QUICK:
                return new QuickSort();

            default:
                throw new IllegalArgumentException("Tipo de ordenação inválido: " + tipo);
        }
    }
}