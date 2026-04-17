package br.upe.analisealgoritmos.experimentos.runners;

/*
 * ============================================================
 * CLASSE: BenchmarkRunner
 * ============================================================
 *
 * OBJETIVO:
 * Orquestrar a execução de todos os experimentos do sistema.
 *
 * RESPONSABILIDADES:
 * ✔ Executar múltiplos experimentos
 * ✔ Permitir filtro via CLI
 * ✔ Consolidar resultados
 * ✔ Integrar com CSVExporter (pipeline Python)
 *
 * EXECUÇÃO:
 * ▶ ./gradlew run
 * ▶ ./gradlew run --args="busca"
 *
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

import br.upe.analisealgoritmos.experimentos.ExperimentoBuscaGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoEstruturasGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoGrafosGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoLista03;
import br.upe.analisealgoritmos.experimentos.ExperimentoOrdenacaoGrafico;
import br.upe.analisealgoritmos.experimentos.base.Experimento;
import br.upe.analisealgoritmos.utils.exportacao.CSVExporter;

public class BenchmarkRunner {

    public static void main(String[] args) {

        System.out.println("🚀 Iniciando pipeline de benchmarks...\n");

        /*
         * ========================================================
         * LISTA DE EXPERIMENTOS
         * ========================================================
         */
        List<Experimento> experimentos = new ArrayList<>(List.of(
                new ExperimentoBuscaGrafico(),
                new ExperimentoOrdenacaoGrafico(),
                new ExperimentoEstruturasGrafico(),
                new ExperimentoGrafosGrafico(),
                new ExperimentoLista03(),
                new MemoryBenchmarkRunner()
        ));

        /*
         * ========================================================
         * FILTRO VIA CLI (OPCIONAL)
         * ========================================================
         */
        if (args.length > 0) {

            String filtro = args[0].toLowerCase();

            experimentos = experimentos.stream()
                    .filter(e -> e.getNome().toLowerCase().contains(filtro))
                    .toList();

            System.out.println("🔎 Filtro aplicado: " + filtro + "\n");
        }

        /*
         * ========================================================
         * EXECUÇÃO DOS EXPERIMENTOS
         * ========================================================
         */
        List<String[]> resultados = new ArrayList<>();

        for (Experimento exp : experimentos) {

            System.out.println("▶ Executando: " + exp.getNome());

            try {

                List<String[]> resultadoExp = exp.executar();

                resultados.addAll(resultadoExp);

                System.out.println("✅ Concluído: " + exp.getNome() + "\n");

            } catch (Exception e) {

                System.out.println("❌ Erro em: " + exp.getNome());
                e.printStackTrace();
            }
        }

        /*
         * ========================================================
         * EXPORTAÇÃO FINAL
         * ========================================================
         */
        if (resultados.isEmpty()) {

            System.out.println("⚠ Nenhum resultado gerado.");
            return;
        }

        CSVExporter.salvarPipeline(resultados);

        System.out.println("📁 Resultados exportados com sucesso!");
        System.out.println("🎉 Pipeline finalizada!");
    }
}