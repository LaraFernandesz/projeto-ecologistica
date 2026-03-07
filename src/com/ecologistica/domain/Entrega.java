package com.ecologistica.domain;

import com.ecologistica.domain.enums.StatusEntrega;

public class Entrega {

    private Long id;
    private String clienteOuEndereco; // opcional
    private Ponto ponto;
    private StatusEntrega status;

    public Entrega() {}

    public Entrega(Long id, String clienteOuEndereco, Ponto ponto, StatusEntrega status) {
        this.id = id;
        this.clienteOuEndereco = clienteOuEndereco;
        this.ponto = ponto;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getClienteOuEndereco() {
        return clienteOuEndereco;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entrega{id=" + id +
                ", clienteOuEndereco='" + clienteOuEndereco + '\'' +
                ", ponto=" + ponto +
                ", status=" + status +
                '}';
    }
}