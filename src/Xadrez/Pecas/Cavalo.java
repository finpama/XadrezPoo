package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;

public class Cavalo extends Peca {
    public Cavalo(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro, pecaBranca);
        setNome("C");
    }

    @Override
    public boolean[][] isMovimentoValido(int lin, int col){
        boolean[][] matriz = new boolean[8][8];
        Peca[][] posicoes = tabuleiro.getMatrizPosicoes();

        //Movimentação do cavalo
        int[][] direcoes = {
                {-2, 1}, {-2, -1}, // dois pra cima, um pro lado
                {2, 1}, {2, -1}, // dois pra baixo, um pro lado
                {1, -2}, {1, 2}, // um pra baixo, dois pro lado
                {-1, -2}, {-1, 2} // um pra cima, dois pro lado
        };
        for (int[] dir: direcoes){
            int i = lin + dir[0];
            int j = col + dir[1];

            if (i >= 0 && i < 8 && j >= 0 && j < 8){
                Peca alvo = posicoes[i][j];
                if (alvo == null || alvo.isPecaBranca() != this.pecaBranca){
                    matriz[i][j] = true;
                }
            }
        }
        return matriz;
    }
}
