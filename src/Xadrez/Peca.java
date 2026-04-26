package Xadrez;
// Pacote contendo regras padrão do xadrez, sobre o tabuleiro e suas peças

import Xadrez.Tabuleiro;

public abstract class Peca {

    protected Tabuleiro tabuleiro;
    protected boolean pecaBranca;
    protected String nome;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public boolean isPecaBranca() {
        return this.pecaBranca;
    }
    public String getNome() {
        return this.nome;
    }
    public abstract boolean isMovimentoValido(int lin, int col, int linDestino, int colDestino);
}

