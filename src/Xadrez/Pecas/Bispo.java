package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;


public class Bispo extends Peca {
    public Bispo(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro);
        this.pecaBranca = pecaBranca;
        this.nome = "B";
    }

    @Override
    public boolean isMovimentoValido(int lin, int col, int linDestino, int colDestino) {
        return false;
    }
}
