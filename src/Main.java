import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Xadrez.Tabuleiro;
// Pacote contendo regras padrão do xadrez, sobre o tabuleiro e suas peças


public class Main {

    public static void limpaTela() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Scanner scanner = new Scanner(System.in);
        String resultadoAnterior = "Primeira rodada";
        boolean isFim = false;

        limpaTela();
        System.out.println("  --------------------  Xadrez  -------------------\n");
        System.out.println("Instruções:");
        System.out.println(" - Para mover uma peça diga a inicial, a posição onde está, '-' e a posição para onde irá. Ex: Cb2-c3 (Cavalo em b2 para c3).");
        System.out.println(" - Caso exista uma peça do oponente no destino indicado, haverá uma captura.");
        System.out.println("\nIniciais (em português)");
        System.out.println("B - Bispo");
        System.out.println("C - Cavalo");
        System.out.println("D - Dama");
        System.out.println("P - Peão");
        System.out.println("R - Rei");
        System.out.println("T - Torre");

        System.out.println("\nPressione enter para continuar...");
        scanner.nextLine();

        while (true) {
            limpaTela();
            System.out.println("  --------------------  Xadrez  -------------------\n");
            System.out.println("  Resultado última rodada: " + resultadoAnterior);
            System.out.println("  Rodada atual: " + (tabuleiro.getNumRodadas() + 1) + "\n");

            tabuleiro.renderizarNoTerminal();

            if (tabuleiro.isVezBrancas()) {
                System.out.print("\nVez das brancas: ");
            } else {
                System.out.print("\nVez das pretas: ");
            }

            String resposta = scanner.nextLine();

            if (resposta.equals("exit") || resposta.equals("sair")) {
                break;
            }

            Pattern formatoNotacao = Pattern.compile("([BCDPRT][a-h][1-8]\\-[a-h][1-8])", Pattern.CASE_INSENSITIVE);
            Matcher notacao = formatoNotacao.matcher(resposta);

            if (notacao.find()) {
                resultadoAnterior = tabuleiro.moverPeca(notacao.group());

            } else {
                resultadoAnterior = "O movimento não foi escrito na notação correta";
            }

            if (!tabuleiro.getVencedor().isEmpty()) {
                break;
            }

        }

        System.out.println("Jogo finalizado...");
        if (!tabuleiro.getVencedor().isEmpty()) {
            System.out.printf("As %s venceram!!!!!%n", tabuleiro.getVencedor());
        }

    }
}