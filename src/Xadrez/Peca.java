package Xadrez;
// Pacote contendo regras padrão do xadrez, sobre o tabuleiro e suas peças

import Xadrez.Tabuleiro;

public abstract class Peca {

    protected Tabuleiro tabuleiro;
    protected boolean pecaBranca;
    protected String nome;

    public Peca(Tabuleiro tabuleiro, boolean pecaBranca) {
        setTabuleiro(tabuleiro);
        setPecaBranca(pecaBranca);
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    protected void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    protected void setPecaBranca(boolean pecaBranca) {
        this.pecaBranca = pecaBranca;
    }
    public boolean isPecaBranca() {
        return pecaBranca;
    }


    protected void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    public abstract boolean[][] isMovimentoValido(int lin, int col);
}

