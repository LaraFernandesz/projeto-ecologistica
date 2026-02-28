package com.ecologistica.domain;

public class Deposito {
    private Long id;
    private String nome;
    private Ponto ponto;

    public Deposito(Long id, String nome, Ponto ponto) {
        this.id = id;
        this.nome = nome;
        this.ponto = ponto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Ponto getPonto() { return ponto; }

    @Override
    public String toString() {
        return "Deposito [ID=" + id + ", Nome=" + nome + ", Ponto=" + ponto + "]";
    }
}