package br.upe.analisealgoritmos.experimentos;

/*
 * ============================================================
 * CLASSE: ExperimentoOrdenacaoCasosGrafico
 * ============================================================
 *
 * OBJETIVO:
 * Comparar melhor vs pior caso
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.upe.analisealgoritmos.utils.config.ConfigBenchmark;
import br.upe.analisealgoritmos.utils.dados.GeradorDados;
import br.upe.analisealgoritmos.utils.exportacao.CSVExporter;

public class ExperimentoOrdenacaoCasosGrafico {

    public static void main(String[] args) {

        System.out.println("📊 Casos de ordenação");

        List<String[]> resultados = new ArrayList<>();

        for (int n : ConfigBenchmark.TAMANHOS_PADRAO) {

            int[] melhor = GeradorDados.gerar(n, GeradorDados.TipoEntrada.ORDENADO);
            int[] pior = GeradorDados.gerar(n, GeradorDados.TipoEntrada.REVERSO);

            long t1 = medir(melhor);
            long t2 = medir(pior);

            resultados.add(new String[]{n+"","melhor","Insertion",t1+""});
            resultados.add(new String[]{n+"","pior","Insertion",t2+""});
        }

        CSVExporter.salvarPipeline(resultados);

        System.out.println("✅ Casos finalizados");
    }

    private static long medir(int[] arr) {

        int[] copia = Arrays.copyOf(arr, arr.length);

        long ini = System.nanoTime();

        for (int i = 1; i < copia.length; i++) {
            int key = copia[i];
            int j = i - 1;

            while (j >= 0 && copia[j] > key) {
                copia[j + 1] = copia[j];
                j--;
            }

            copia[j + 1] = key;
        }

        return System.nanoTime() - ini;
    }
}