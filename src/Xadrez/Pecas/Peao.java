package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;

public class Peao extends Peca {
    public Peao(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro);
        this.pecaBranca = pecaBranca;
        this.nome = "P";
    }

    @Override
    public boolean isMovimentoValido(int lin, int col, int linDestino, int colDestino) {
        return false;
    }
}
