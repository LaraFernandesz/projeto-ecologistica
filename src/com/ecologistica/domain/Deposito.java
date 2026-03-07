package com.ecologistica.domain;

public class Deposito {

    private Long id;
    private String nome;
    private Ponto ponto;

    public Deposito() {}

    public Deposito(Long id, String nome, Ponto ponto) {
        this.id = id;
        this.nome = nome;
        this.ponto = ponto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPonto(Ponto ponto) {
        this.ponto = ponto;
    }

    @Override
    public String toString() {
        return "Deposito{id=" + id +
                ", nome='" + nome + '\'' +
                ", ponto=" + ponto +
                '}';
    }
}