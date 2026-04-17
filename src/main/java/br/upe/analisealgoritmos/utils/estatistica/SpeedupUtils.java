package br.upe.analisealgoritmos.utils.estatistica;

/*
 * ============================================================
 * CLASSE: SpeedupUtils
 * ============================================================
 *
 * OBJETIVO:
 * Calcular speedup relativo entre algoritmos.
 *
 * DEFINIÇÃO:
 * Speedup = tempo_algoritmo / tempo_melhor
 *
 * ============================================================
 */

import java.util.HashMap;
import java.util.Map;

public class SpeedupUtils {

    /*
     * ============================================================
     * CALCULAR SPEEDUP
     * ============================================================
     */
    public static Map<String, Double> calcularSpeedup(Map<String, Double> medias) {

        Map<String, Double> resultado = new HashMap<>();

        if (medias == null || medias.isEmpty()) {
            return resultado;
        }

        double melhor = medias.values()
                .stream()
                .min(Double::compare)
                .orElse(1.0);

        for (Map.Entry<String, Double> entry : medias.entrySet()) {

            double valor = entry.getValue();
            double speedup = (melhor == 0) ? 1.0 : valor / melhor;

            resultado.put(entry.getKey(), speedup);
        }

        return resultado;
    }

    /*
     * ============================================================
     * MELHOR ALGORITMO
     * ============================================================
     */
    public static String melhorAlgoritmo(Map<String, Double> medias) {

        return medias.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }
}