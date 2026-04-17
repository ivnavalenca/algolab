package br.upe.analisealgoritmos.utils.exportacao;

/*
 * ============================================================
 * CLASSE: InterpretadorResultados
 * ============================================================
 *
 * OBJETIVO:
 * Ler CSV de resultados e transformar em estrutura de dados.
 *
 * ============================================================
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterpretadorResultados {

    public static Map<String, List<Double>> carregar(String caminho) {

        Map<String, List<Double>> dados = new HashMap<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(caminho));
            br.readLine(); // cabeçalho

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] v = linha.split(",");

                String algoritmo = v[2];
                double tempo = Double.parseDouble(v[3]);

                dados.putIfAbsent(algoritmo, new ArrayList<>());
                dados.get(algoritmo).add(tempo);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dados;
    }
}