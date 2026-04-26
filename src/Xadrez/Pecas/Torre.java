package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;


public class Torre extends Peca {
    public Torre(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro);
        this.pecaBranca = pecaBranca;
        this.nome = "T";
    }

    @Override
    public boolean isMovimentoValido(int lin, int col, int linDestino, int colDestino) {
        return false;
    }
}

