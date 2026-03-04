package com.ecologistica.domain;

public class Veiculo {
    private Long id;
    private String placa;
    private Ponto pontoAtual;
    private String status; // Ex: "LIVRE", "OCUPADO"

    public Veiculo(Long id, String placa, Ponto pontoAtual) {
        this.id = id;
        this.placa = placa;
        this.pontoAtual = pontoAtual;
        this.status = "LIVRE";
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getPlaca() { return placa; }
    public Ponto getPontoAtual() { return pontoAtual; }
    public void setPontoAtual(Ponto pontoAtual) { this.pontoAtual = pontoAtual; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}