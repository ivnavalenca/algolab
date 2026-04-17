package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoLista03
 * ============================================================
 *
 * OBJETIVO:
 * Executar experimentos adicionais (Lista 03).
 *
 * FOCO:
 * ✔ Comparação de estratégias de busca
 * ✔ Demonstração de impacto de ordenação
 *
 * PADRÃO:
 * ✔ Interface Experimento
 * ✔ GeradorDados
 * ✔ ConfigBenchmark
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;
import br.upe.analisealgoritmos.utils.dados.GeradorDados;

public class ExperimentoLista03 implements Experimento {

    @Override
    public String getNome() {
        return "Lista03";
    }

    @Override
    public List<String[]> executar() {

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            /*
             * ========================================================
             * DADOS ALEATÓRIOS
             * ========================================================
             */
            int[] dados = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ALEATORIO);
            int alvo = dados[n / 2];

            /*
             * ========================================================
             * BUSCA LINEAR
             * ========================================================
             */
            long inicio = System.nanoTime();
            buscaLinear(dados, alvo);
            long tempoLinear = System.nanoTime() - inicio;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "lista03",
                    "Linear",
                    String.valueOf(tempoLinear)
            });

            /*
             * ========================================================
             * BUSCA BINÁRIA (DADOS ORDENADOS)
             * ========================================================
             */
            int[] ordenado = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ORDENADO);

            inicio = System.nanoTime();
            buscaBinaria(ordenado, alvo);
            long tempoBinaria = System.nanoTime() - inicio;

            resultados.add(new String[]{
                    String.valueOf(n),
                    "lista03",
                    "Binaria",
                    String.valueOf(tempoBinaria)
            });
        }

        return resultados;
    }

    /*
     * ============================================================
     * BUSCA LINEAR
     * ============================================================
     */
    private int buscaLinear(int[] arr, int alvo) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == alvo) return i;
        }

        return -1;
    }

    /*
     * ============================================================
     * BUSCA BINÁRIA
     * ============================================================
     */
    private int buscaBinaria(int[] arr, int alvo) {

        int l = 0, r = arr.length - 1;

        while (l <= r) {

            int m = (l + r) / 2;

            if (arr[m] == alvo) return m;

            if (arr[m] < alvo) l = m + 1;
            else r = m - 1;
        }

        return -1;
    }
}