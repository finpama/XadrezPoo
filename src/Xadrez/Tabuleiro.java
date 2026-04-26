package Xadrez;

import Xadrez.Pecas.*;

public class Tabuleiro {
    private Peca[][] matrizPosicoes;

    public Peca[][] getMatrizPosicoes() {
        return matrizPosicoes;
    }
    public void setMatrizPosicoes() {
        this.matrizPosicoes = new Peca[8][8]; // cria uma matriz 8x8 para guardar as posições
    }

    public Tabuleiro() {
        setMatrizPosicoes();
        setPecasIniciais();
    }

    private void setPecasIniciais() {

        //Peças Pretas
        this.matrizPosicoes[0][0] = new Torre(this, false);
        this.matrizPosicoes[0][1] = new Cavalo(this, false);
        this.matrizPosicoes[0][2] = new Bispo(this, false);
        this.matrizPosicoes[0][3] = new Dama(this, false);
        this.matrizPosicoes[0][4] = new Rei(this, false);
        this.matrizPosicoes[0][5] = new Bispo(this, false);
        this.matrizPosicoes[0][6] = new Cavalo(this, false);
        this.matrizPosicoes[0][7] = new Torre(this, false);

        for(int i = 0; i < 8; i++) {
            matrizPosicoes[1][i] = new Peao(this, false);
        }

        //Peças Brancas
        this.matrizPosicoes[7][0] = new Torre(this, true);
        this.matrizPosicoes[7][1] = new Cavalo(this, true);
        this.matrizPosicoes[7][2] = new Bispo(this, true);
        this.matrizPosicoes[7][3] = new Dama(this, true);
        this.matrizPosicoes[7][4] = new Rei(this, true);
        this.matrizPosicoes[7][5] = new Bispo(this, true);
        this.matrizPosicoes[7][6] = new Cavalo(this, true);
        this.matrizPosicoes[7][7] = new Torre(this, true);

        for(int i = 0; i < 8; i++) {
            matrizPosicoes[6][i] = new Peao(this, true);
        }

    }

    public void renderizarNoTerminal() {

        // Standard ANSI Escape Codes
        final String RESET = "\u001B[0m";
        final String GRAY = "\u001B[90m";

        System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");

        for (int i = 0; i < matrizPosicoes.length; i++) {
            Peca[] linhaTabuleiro = matrizPosicoes[i];

            StringBuilder linhaTerminal = new StringBuilder();
            String numLinha = Integer.toString(Math.abs(i - 8));
            linhaTerminal.append(numLinha.concat(" │  "));

            for (Peca pecaTabuleiro : linhaTabuleiro) {
                String nomePeca;

                if (pecaTabuleiro != null) {
                    if (!(pecaTabuleiro.isPecaBranca())) { // se NÃO for peça branca
                        linhaTerminal.append(GRAY);
                    }
                    nomePeca = pecaTabuleiro.getNome();

                } else {
                    nomePeca = " ";
                }

                linhaTerminal.append(nomePeca);
                linhaTerminal.append(RESET);

                linhaTerminal.append("  │  ");
            }

            System.out.println(linhaTerminal);
            if ( !(i == 7) ) {
                System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
            }

        }

        System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
        System.out.println("     a     b     c     d     e     f     g     h   ");

    }


}
