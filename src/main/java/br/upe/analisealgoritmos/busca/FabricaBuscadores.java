package br.upe.analisealgoritmos.busca;

/*
 * ============================================================
 * FÁBRICA DE BUSCADORES (Factory Pattern)
 * ============================================================
 *
 * OBJETIVO:
 * Centralizar a criação dos algoritmos de busca.
 *
 * Em vez de usar:
 *   new BuscaLinear()
 *
 * usamos:
 *   FabricaBuscadores.criar(TipoBusca.LINEAR)
 *
 * ============================================================
 * BENEFÍCIOS:
 *
 * ✔ Desacoplamento
 * ✔ Facilidade de manutenção
 * ✔ Extensibilidade
 * ✔ Código mais limpo
 *
 * ============================================================
 */

public class FabricaBuscadores {

    /*
     * Método responsável por criar o algoritmo desejado
     */
    public static Buscador criar(TipoBusca tipo) {

        switch (tipo) {

            case LINEAR:
                return new BuscaLinear();

            case BINARIA:
                return new BuscaBinaria();

            default:
                throw new IllegalArgumentException(
                        "Tipo de busca inválido: " + tipo
                );
        }
    }
}