package Xadrez;

import Xadrez.Pecas.*;

import java.util.Locale;

public class Tabuleiro {
    private Peca[][] matrizPosicoes;
    private boolean isVezBrancas;
    private int numRodadas;
    private String vencedor = "";
    private String avisoXeque = "";

    public String getAvisoXeque() {
        return avisoXeque;
    }

    public String getVencedor() {
        return vencedor;
    }

    public boolean isVezBrancas() {
        return isVezBrancas;
    }
    public int getNumRodadas() {
        return numRodadas;
    }
    public void proxRodada() {
        numRodadas++;
        isVezBrancas = numRodadas % 2 == 0;
    }

    public Peca[][] getMatrizPosicoes() {
        return matrizPosicoes;
    }
    public void setMatrizPosicoes() {
        this.matrizPosicoes = new Peca[8][8]; // cria uma matriz 8x8 para guardar as posições
    }

    public Tabuleiro() {
        isVezBrancas = true;
        numRodadas = 0;

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
        final String RED = "\u001B[31;1m";

        System.out.println("  " + RED + getAvisoXeque() + RESET
        );
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

    public String letraParaPeca(String letra) {
        return switch (letra) {
            case "B" -> "Bispo";
            case "C" -> "Cavalo";
            case "D" -> "Dama";
            case "P" -> "Peão";
            case "R" -> "Rei";
            case "T" -> "Torre";
            default -> "Desconhecido";
        };
    }

    public int letraParaIndex(String letra) {
        return switch (letra) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            default -> -1;
        };
    }


    // xeque
    private int[] achaRei(boolean isReiBranco) {
        for (int i= 0; i<8; i++) {
            for (int j= 0; j<8; j++) {
                Peca peca = matrizPosicoes[i][j];
                if (peca != null && peca.getNome().equals("R") && peca.isPecaBranca() == isReiBranco) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public boolean emXeque(boolean isReiBranco) {
        int[] posRei = achaRei(isReiBranco);
        if (posRei == null){
            return false;
        }
        for (int i= 0; i <8; i++) {
            for (int j= 0; j<8; j++) {
                Peca peca = matrizPosicoes[i][j];
                if (peca != null && peca.isPecaBranca() != isReiBranco) {
                    boolean[][] movInimigo = peca.isMovimentoValido(i, j);
                    if (movInimigo[posRei[0]][posRei[1]]){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private Peca checaPromocao(Peca peca, int lin) {
        if (peca.getNome().equals("P") && (lin == 7 || lin == 0)) {
            return new Dama(this, peca.isPecaBranca());
        } else {
            return peca;
        }
    }

    private void checaCapturaRei(int  colFinal, int linFinal) {
        boolean campoNaoVazio = matrizPosicoes[linFinal][colFinal] != null;

        if (campoNaoVazio) {
            boolean pecaCapturadaIsRei = matrizPosicoes[linFinal][colFinal].getNome().equals("R");

            if (pecaCapturadaIsRei){
                if (isVezBrancas()) {
                    vencedor = "Brancas";
                } else {
                    vencedor = "Pretas";
                }
            }
        }
    }

    public String moverPeca(String str_colInicial, String str_linInicial, String str_colFinal, String str_linFinal) { // Retorna o resultado do movimento

        int colInicial = letraParaIndex(str_colInicial);

        int linInicial = Math.abs(Integer.parseInt(str_linInicial) - 8) ; // inverte a direção da linha para ir de 8 a 1
        //if (linInicial < 0) { linInicial--; }

        int colFinal = letraParaIndex(str_colFinal);

        int linFinal = Math.abs(Integer.parseInt(str_linFinal) - 8); // inverte a direção da linha para ir de 8 a 1
        //if (linFinal < 0) { linFinal--; }


        String notacaoCorrigida = str_colInicial + str_linInicial + "-" + str_colFinal + str_linFinal;

        //checar peça

        try {
            Peca peca = matrizPosicoes[linInicial][colInicial];

            if (peca.isPecaBranca() != isVezBrancas()) {
                return "(%s) Não é possível mover uma peça rival.".formatted(notacaoCorrigida);

            } else {
                boolean[][] posicoesPossiveis = peca.isMovimentoValido(linInicial, colInicial);

                if (posicoesPossiveis[linFinal][colFinal]) { // se o movimento para essas coordenadas for possível move a peça
                    peca = checaPromocao(peca, linFinal);
                    checaCapturaRei(colFinal, linFinal);

                    matrizPosicoes[linFinal][colFinal] = peca;
                    matrizPosicoes[linInicial][colInicial] = null;

                    // Verifica se após o movimento está ocorrendo um cheque
                    if (emXeque(!isVezBrancas())) {

                        String cor;
                        if (isVezBrancas()) {
                            cor = "preto";
                        } else  {
                            cor = "branco";
                        }

                        this.avisoXeque = " O rei %s está em Xeque!".formatted(cor);

                    } else {
                        this.avisoXeque = "";
                    }

                    proxRodada();
                    return "(%s) %s foi movido(a) para %s%s.".formatted(notacaoCorrigida, letraParaPeca(peca.getNome()), str_colFinal, str_linFinal);

                } else {
                    return "(%s) Não é permitido mover a peça %s em %s%s para %s%s.".formatted(notacaoCorrigida, letraParaPeca(peca.getNome()), str_colInicial, str_linInicial, str_colFinal, str_linFinal);
                }
            }

        } catch (NullPointerException e) {
            return "(%s) Não existe peça na posição especificada.".formatted(notacaoCorrigida);
        }



    }

}
