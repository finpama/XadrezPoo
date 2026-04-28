package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;

public class Peao extends Peca {
    public Peao(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro, pecaBranca);
        setNome("P");
    }

    @Override
    public boolean[][] isMovimentoValido(int lin, int col){
        boolean[][] matriz = new boolean[8][8];
        Peca[][] posicoes = tabuleiro.getMatrizPosicoes();

        //Movimentação do peão
        int direcao;
        if (pecaBranca){
            direcao = -1;
        } else {
            direcao = 1;
        }

        int i = lin+direcao;
        if (i >= 0 && i <8 && posicoes[i][col] == null){
            matriz[i][col] = true;

            int andaDois = lin + (direcao*2);
            boolean inicio = (pecaBranca && lin == 6) || (!pecaBranca && lin == 1);
            if (inicio && posicoes[andaDois][col] == null){
                matriz[andaDois][col] = true;
            }
            int[] colCaptura = {col-1, col+1};
            for (int j: colCaptura) {
                if (i>= 0 && i < 8 && j >= 0 && j< 8){
                    Peca alvo = posicoes[i][j];
                    if (alvo != null && alvo.isPecaBranca() != this.pecaBranca){
                        matriz[i][j] = true;
                    }
                }
            }
        }
        return matriz;

    }
}