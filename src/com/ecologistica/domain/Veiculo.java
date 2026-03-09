package com.ecologistica.domain;

import com.ecologistica.domain.enums.StatusVeiculo;

public class Veiculo {

    private Long id;
    private String placa;
    private Ponto pontoAtual;
    private StatusVeiculo status;

    public Veiculo(Long id, String placa, Ponto pontoAtual) {
        this.id = id;
        this.placa = placa;
        this.pontoAtual = pontoAtual;
        this.status = StatusVeiculo.LIVRE;
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Veiculo{id=" + id +
                ", placa='" + placa + '\'' +
                ", pontoAtual=" + pontoAtual +
                ", status=" + status +
                '}';
    }
}