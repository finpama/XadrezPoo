package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;


public class Bispo extends Peca {
    public Bispo(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro, pecaBranca);
        setNome("B");
    }

    @Override
    public boolean[][] isMovimentoValido(int lin, int col){
        boolean[][] matriz = new boolean[8][8];
        Peca[][] posicoes = tabuleiro.getMatrizPosicoes();

        //A movimentação do bispo
        int[][] direcoes = {
                {-1, -1}, //Noroeste
                {-1, 1}, //Nordeste
                {1, 1}, //Sudeste
                {1, -1}, //Sudoeste
        };

        for(int[] dir: direcoes) {
            int i = lin + dir[0];
            int j = col + dir[1];

            while (i >= 0 && i < 8 && j >= 0 && j < 8){
                if (posicoes[i][j] == null){
                    matriz[i][j] = true;
                } else{
                    if (posicoes[i][j].isPecaBranca() != this.pecaBranca) {
                        matriz[i][j] = true;
                    }
                    break;
                }
                i += dir[0];
                j += dir[1];
            }
        }
        return matriz;
    }

}
