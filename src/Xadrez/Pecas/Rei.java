package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;

public class Rei extends Peca {
    public Rei(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro);
        this.pecaBranca = pecaBranca;
        this.nome = "R";
    }

    @Override
    public boolean isMovimentoValido(int lin, int col, int linDestino, int colDestino) {
        return false;
    }
}
