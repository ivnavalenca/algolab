package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoBuscaGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Benchmark de algoritmos de busca.
 *
 * ALGORITMOS:
 * ✔ Busca Linear
 * ✔ Busca Binária
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

public class ExperimentoBuscaGrafico implements Experimento {

    @Override
    public String getNome() {
        return "Busca";
    }

    @Override
    public List<String[]> executar() {

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            int[] dados = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ORDENADO);
            int alvo = dados[n / 2];

            long ini = System.nanoTime();
            buscaLinear(dados, alvo);
            long t1 = System.nanoTime() - ini;

            ini = System.nanoTime();
            buscaBinaria(dados, alvo);
            long t2 = System.nanoTime() - ini;

            resultados.add(new String[]{n+"","ordenado","Linear",t1+""});
            resultados.add(new String[]{n+"","ordenado","Binaria",t2+""});
        }

        return resultados;
    }

    private int buscaLinear(int[] arr, int alvo) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == alvo) return i;
        return -1;
    }

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