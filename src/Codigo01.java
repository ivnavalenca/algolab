// Codigo01.java

public class Codigo01 {
    public static void main(String[] args) {

        // Vetor de exemplo
        int[] a = {3, 5, 2, 8, 1};

        // N representa o tamanho do vetor
        int N = a.length;

        // Inicializa o maior valor com o primeiro elemento
        double max = a[0];

        // Variável para contar quantas vezes a "terceira linha" é executada
        int contador = 0;

        /*
         * QUESTÃO:
         * Quantas vezes a terceira linha do código é executada?
         *
         * A "terceira linha" é o IF dentro do FOR:
         * if (a[i] > max)
         *
         * Esse IF será executado toda vez que o laço FOR rodar.
         */

        // O laço começa em i = 1 e vai até N-1
        for (int i = 1; i < N; i++) {

            // Cada iteração representa uma execução da linha analisada
            contador++;

            // Verifica se o elemento atual é maior que o máximo
            if (a[i] > max) {
                max = a[i];
            }
        }

        // Saída dos resultados
        System.out.println("Maior valor: " + max);
        System.out.println("Quantidade de execuções da linha: " + contador);

        /*
         * RESPOSTA:
         *
         * O laço executa de i = 1 até i = N-1
         * Portanto, ele executa (N - 1) vezes.
         *
         * Logo, a terceira linha (IF) é executada:
         *
         * N - 1 vezes
         *
         * EXEMPLO:
         * Se N = 5 → executa 4 vezes
         */
    }
}