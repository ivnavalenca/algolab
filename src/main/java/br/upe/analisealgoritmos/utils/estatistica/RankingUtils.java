package br.upe.analisealgoritmos.utils.estatistica;

/*
 * ============================================================
 * CLASSE: RankingUtils
 * ============================================================
 *
 * OBJETIVO:
 * Gerar ranking de algoritmos baseado no desempenho.
 *
 * ============================================================
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingUtils {

    /*
     * ============================================================
     * GERAR RANKING (MENOR TEMPO = MELHOR)
     * ============================================================
     */
    public static Map<String, Double> gerarRanking(Map<String, Double> medias) {

        return medias.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}