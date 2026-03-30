package br.upe.analisealgoritmos.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * ============================================================
 * CLASSE: RankingUtils
 * ============================================================
 *
 * OBJETIVO:
 * Gerar ranking de algoritmos baseado no desempenho (menor = melhor)
 *
 * RESPONSABILIDADES:
 * ✔ Ordenar algoritmos por tempo médio
 * ✔ Retornar ranking pronto para uso
 *
 * ============================================================
 */

public class RankingUtils {

    /*
     * ============================================================
     * GERA RANKING
     * ============================================================
     */
    public static List<Map.Entry<String, Double>> gerarRanking(Map<String, Double> medias) {

        return medias.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // menor tempo primeiro
                .collect(Collectors.toList());
    }
}