package com.ecologistica.domain;

public class Entrega {
    private Long id;
    private Ponto ponto;
    private String status;

    public Entrega() {}

    public Entrega(Long id, Ponto ponto, String status) {
        this.id = id;
        this.ponto = ponto;
        this.status = status;
    }

    public Long getId() { return id; }
    public Ponto getPonto() { return ponto; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}