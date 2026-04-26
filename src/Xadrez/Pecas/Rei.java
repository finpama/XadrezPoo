package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;

public class Rei extends Peca {
    public Rei(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro, pecaBranca);
        setNome("R");
    }

    @Override
    public boolean[][] isMovimentoValido(int lin, int col){
        boolean[][] matriz = new boolean[8][8];
        Peca[][] posicoes = tabuleiro.getMatrizPosicoes();

        //Movimentação do rei
        int[][] direcoes = {
                {-1, -1}, //Noroeste
                {-1, 1}, //Nordeste
                {1, 1}, //Sudeste
                {1, -1}, //Sudoeste
                {-1, 0}, //Cima
                {0, -1},//Esquerda
                {1, 0}, //Baixo
                {0, 1}, //Direita
        };
        for(int[] dir: direcoes){
            int i = lin + dir[0];
            int j = col + dir[1];

            if (i >= 0 && i < 8 && j >= 0 && j < 8){
                Peca alvo = posicoes[i][j];
                if(alvo == null || alvo.isPecaBranca() != this.pecaBranca){
                    matriz[i][j] = true;
                }
            }
        }
        return matriz;
    }
}
