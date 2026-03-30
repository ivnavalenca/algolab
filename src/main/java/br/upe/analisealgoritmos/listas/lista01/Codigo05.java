// Codigo05.java

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Codigo05 {

    public static void main(String[] args) {

        /*
         * QUESTÃO:
         * Escolher a melhor estrutura de dados para cada cenário:
         *
         * a) Inserir/remover no início e no fim da lista
         * b) Sorteios com acesso rápido aos elementos
         */

        // ==============================
        // a) Inserções no início e fim
        // ==============================

        /*
         * Melhor escolha: LinkedList
         *
         * Por quê?
         * - Inserir no início → O(1)
         * - Inserir no fim → O(1)
         * - Remover no início → O(1)
         * - Remover no fim → O(1)
         */

        LinkedList<Integer> lista = new LinkedList<>();

        // Inserções
        lista.addFirst(10);
        lista.addLast(20);
        lista.addFirst(5);

        System.out.println("Lista após inserções: " + lista);

        // Remoções
        lista.removeFirst();
        lista.removeLast();

        System.out.println("Lista após remoções: " + lista);

        /*
         * Se usássemos ArrayList aqui:
         * - Inserir no início seria O(n) (desloca elementos)
         */

        // ==============================
        // b) Sorteios com acesso rápido
        // ==============================

        /*
         * Melhor escolha: ArrayList (ou vetor)
         *
         * Por quê?
         * - Acesso por índice → O(1)
         * - Ideal para sorteios aleatórios
         */

        ArrayList<Integer> numeros = new ArrayList<>();

        // Preenche a lista
        for (int i = 1; i <= 10; i++) {
            numeros.add(i);
        }

        // Sorteio aleatório
        Random rand = new Random();
        int indice = rand.nextInt(numeros.size());

        int sorteado = numeros.get(indice);

        System.out.println("Número sorteado: " + sorteado);

        /*
         * RESPOSTA:
         *
         * a) Melhor estrutura:
         * LinkedList (lista encadeada)
         *
         * Motivo:
         * Inserções/remoções no início e fim são O(1)
         *
         *
         * b) Melhor estrutura:
         * Array (vetor) ou ArrayList
         *
         * Motivo:
         * Acesso direto por índice é O(1), ideal para sorteios
         *
         *
         * RESUMO:
         *
         * LinkedList → melhor para inserção/remoção
         * ArrayList  → melhor para acesso rápido
         */
    }
}