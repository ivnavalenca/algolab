package br.upe.analisealgoritmos.utils;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * CLASSE: SpeedupUtils
 * ============================================================
 *
 * OBJETIVO:
 * Calcular speedup relativo entre algoritmos
 *
 * DEFINIÇÃO:
 * Speedup = tempo_algoritmo / tempo_melhor
 *
 * INTERPRETAÇÃO:
 * ✔ 1.0x → melhor algoritmo
 * ✔ 2.0x → 2x mais lento
 * ✔ 10x → muito mais lento
 *
 * ============================================================
 */

public class SpeedupUtils {

    /*
     * ============================================================
     * CALCULA SPEEDUP RELATIVO
     * ============================================================
     */
    public static Map<String, Double> calcularSpeedup(Map<String, Double> medias) {

        Map<String, Double> speedup = new HashMap<>();

        // 🔥 encontra o melhor (menor tempo)
        double melhor = medias.values()
                .stream()
                .min(Double::compare)
                .orElse(1.0);

        /*
         * ============================================================
         * CALCULA SPEEDUP
         * ============================================================
         */
        for (String algoritmo : medias.keySet()) {

            double valor = medias.get(algoritmo);

            // evita divisão por zero
            if (melhor == 0) {
                speedup.put(algoritmo, 1.0);
            } else {
                speedup.put(algoritmo, valor / melhor);
            }
        }

        return speedup;
    }
}