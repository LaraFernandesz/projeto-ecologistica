package com.ecologistica.domain;

public class Entrega {
    private Long id;
    private Ponto ponto;
    private String status;  2

    public Entrega() {}
    public Long getId() { return id; }
    public Ponto getPonto() { return ponto; }
}