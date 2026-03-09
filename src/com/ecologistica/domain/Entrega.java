package com.ecologistica.domain;

import com.ecologistica.domain.enums.StatusEntrega;

public class Entrega {

    private Long id;
    private String clienteOuEndereco;
    private Ponto ponto;
    private StatusEntrega status;
    private Double horarioLimite;

    public Entrega() {}

    public Entrega(Long id, String clienteOuEndereco, Ponto ponto, StatusEntrega status, Double horarioLimite) {
        this.id = id;
        this.clienteOuEndereco = clienteOuEndereco;
        this.ponto = ponto;
        this.status = status;
        this.horarioLimite = horarioLimite;
    }

    public Long getId() { return id; }
    public Ponto getPonto() { return ponto; }
    public StatusEntrega getStatus() { return status; }
    public void setStatus(StatusEntrega status) { this.status = status; }
    public Double getHorarioLimite() { return horarioLimite; }


    @Override
    public String toString() {
        return "Entrega{id=" + id +
                ", ponto=" + ponto +
                ", status=" + status +
                ", limite=" + (horarioLimite != null ? horarioLimite : "Sem limite") +
                '}';
    }
}