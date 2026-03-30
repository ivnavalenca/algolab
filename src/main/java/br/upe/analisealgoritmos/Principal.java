package br.upe.analisealgoritmos;

import java.util.Scanner;

import br.upe.analisealgoritmos.experimentos.ExperimentoBuscaGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoEstruturasGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoOrdenacaoCasosGrafico;
import br.upe.analisealgoritmos.experimentos.ExperimentoOrdenacaoGrafico;

/*
 * ============================================================
 * CLASSE PRINCIPAL (VERSÃO FINAL PROFISSIONAL)
 * ============================================================
 *
 * SUPORTA:
 *
 * ✔ Execução automática (benchmark)
 * ✔ Execução em ambiente sem input (CI, Codespaces)
 * ✔ Execução interativa (menu)
 *
 * ============================================================
 */

public class Principal {

    public static void main(String[] args) {

        /*
         * ============================================================
         * MODO AUTOMÁTICO VIA ARGUMENTO
         * ============================================================
         */
        if (args.length > 0 && args[0].equalsIgnoreCase("benchmark")) {

            System.out.println("Modo benchmark ativado...\n");

            executarTodosExperimentos();

            System.out.println("\nExecução finalizada.");
            return;
        }

        /*
         * ============================================================
         * AMBIENTE SEM INPUT (CI / Codespaces)
         * ============================================================
         */
        if (System.console() == null) {

            System.out.println("Ambiente sem entrada interativa.");
            System.out.println("Executando experimento padrão...\n");

            executarTodosExperimentos();

            System.out.println("\nExecução finalizada.");
            return;
        }

        /*
         * ============================================================
         * MODO INTERATIVO (LOCAL)
         * ============================================================
         */
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== ANÁLISE DE ALGORITMOS ===");
            System.out.println("1 - Busca");
            System.out.println("2 - Estruturas");
            System.out.println("3 - Ordenação");
            System.out.println("4 - Ordenação (Casos)");
            System.out.println("5 - Benchmark completo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            /*
             * Evita erro de entrada inválida
             */
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida!");
                scanner.next(); // descarta entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    ExperimentoBuscaGrafico.executar();
                    break;

                case 2:
                    ExperimentoEstruturasGrafico.executar();
                    break;

                case 3:
                    ExperimentoOrdenacaoGrafico.executar();
                    break;

                case 4:
                    ExperimentoOrdenacaoCasosGrafico.executar();
                    break;

                case 5:
                    executarTodosExperimentos();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    /*
     * ============================================================
     * EXECUTA TODOS OS EXPERIMENTOS
     * ============================================================
     */
    private static void executarTodosExperimentos() {

        System.out.println("=== EXECUTANDO TODOS OS EXPERIMENTOS ===\n");

        ExperimentoBuscaGrafico.executar();
        ExperimentoEstruturasGrafico.executar();
        ExperimentoOrdenacaoGrafico.executar();
        ExperimentoOrdenacaoCasosGrafico.executar();
    }
}