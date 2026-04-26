package Xadrez.Pecas;

import Xadrez.Peca;
import Xadrez.Tabuleiro;

public class Cavalo extends Peca {
    public Cavalo(Tabuleiro tabuleiro, boolean pecaBranca) {
        super(tabuleiro);
        this.pecaBranca = pecaBranca;
        this.nome = "C";
    }

    @Override
    public boolean isMovimentoValido(int lin, int col, int linDestino, int colDestino) {
        return false;
    }
}
