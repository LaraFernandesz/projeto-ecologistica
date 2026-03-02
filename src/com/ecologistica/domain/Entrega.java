package com.ecologistica.domain;

import com.ecologistica.domain.enums.StatusEntrega;

import java.util.Objects;

/**
 * Representa uma entrega (parada) a ser visitada pelo veículo.
 */
public class Entrega {

    private final Long id;
    private final String clienteOuEndereco; // opcional
    private final Ponto ponto;
    private StatusEntrega status;

    public Entrega(Long id, String clienteOuEndereco, Ponto ponto) {
        if (id == null) {
            throw new IllegalArgumentException("ID da entrega não pode ser nulo.");
        }
        this.id = id;
        this.clienteOuEndereco = clienteOuEndereco; // opcional, pode ser null
        this.ponto = Objects.requireNonNull(ponto, "Ponto da entrega não pode ser nulo.");
        this.status = StatusEntrega.PENDENTE; // padrão inicial
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

    /**
     * Permite atualizar o status (ex.: Service no futuro controlará as transições).
     */
    public void setStatus(StatusEntrega status) {
        this.status = Objects.requireNonNull(status, "Status da entrega não pode ser nulo.");
    }

    @Override
    public String toString() {
        String cliente = (clienteOuEndereco == null || clienteOuEndereco.isBlank())
                ? "N/A"
                : clienteOuEndereco;

        return String.format("Entrega{id=%d, cliente/endereco='%s', ponto=%s, status=%s}",
                id, cliente, ponto, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entrega)) return false;
        Entrega entrega = (Entrega) o;
        return Objects.equals(id, entrega.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}