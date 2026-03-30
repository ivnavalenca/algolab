package br.upe.analisealgoritmos.utils;

import java.io.BufferedReader;
import java.io.FileReader;

/*
 * ============================================================
 * INTERPRETADOR DE RESULTADOS
 * ============================================================
 *
 * OBJETIVO:
 * Ler o CSV e gerar uma interpretação automática simples
 * dos resultados por cenário.
 *
 * ============================================================
 */

public class InterpretadorResultados {

    public static void interpretar(String caminhoCSV) {

        System.out.println("\n=== INTERPRETAÇÃO AUTOMÁTICA ===\n");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {

            br.readLine(); // pula cabeçalho

            String linha;

            long somaBubble = 0;
            long somaQuick = 0;
            long somaMerge = 0;

            int count = 0;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                String algoritmo = v[2];
                long tempo = Long.parseLong(v[3]);

                switch (algoritmo) {
                    case "Bubble":
                        somaBubble += tempo;
                        break;
                    case "Quick":
                        somaQuick += tempo;
                        break;
                    case "Merge":
                        somaMerge += tempo;
                        break;
                }

                count++;
            }

            long mediaBubble = somaBubble / count;
            long mediaQuick = somaQuick / count;
            long mediaMerge = somaMerge / count;

            System.out.println("📊 Médias gerais:");

            System.out.println("BubbleSort: " + mediaBubble + " ns");
            System.out.println("QuickSort : " + mediaQuick + " ns");
            System.out.println("MergeSort : " + mediaMerge + " ns");

            System.out.println("\n📌 Interpretação:");

            if (mediaQuick < mediaMerge) {
                System.out.println("✔ QuickSort foi mais eficiente em tempo.");
            } else {
                System.out.println("✔ MergeSort apresentou desempenho consistente.");
            }

            if (mediaBubble > mediaQuick) {
                System.out.println("✔ Algoritmos O(n²) são significativamente mais lentos.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao interpretar resultados: " + e.getMessage());
        }
    }
}