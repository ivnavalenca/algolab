package br.upe.analisealgoritmos.utils;

public class ConfigBenchmark {

    public static int getExecucoes(int tamanho) {

        if (tamanho <= 100) return 100;
        if (tamanho <= 1000) return 50;
        if (tamanho <= 5000) return 20;

        return 10;
    }
}