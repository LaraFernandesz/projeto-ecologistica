package com.ecologistica.domain;

import com.ecologistica.domain.enums.StatusVeiculo;

import java.util.Objects;

/**
 * Representa um veículo (van elétrica) da frota.
 */
public class Veiculo {

    private final Long id;
    private final String placaOuNome;

    private Ponto pontoAtual;
    private StatusVeiculo status;

    public Veiculo(Long id, String placaOuNome, Ponto pontoAtual) {
        if (id == null) {
            throw new IllegalArgumentException("ID do veículo não pode ser nulo.");
        }
        if (placaOuNome == null || placaOuNome.isBlank()) {
            throw new IllegalArgumentException("Placa/nome do veículo não pode ser vazio.");
        }

        this.id = id;
        this.placaOuNome = placaOuNome;
        this.pontoAtual = Objects.requireNonNull(pontoAtual, "Ponto atual do veículo não pode ser nulo.");
        this.status = StatusVeiculo.LIVRE; // padrão inicial
    }

    public Long getId() {
        return id;
    }

    public String getPlacaOuNome() {
        return placaOuNome;
    }

    public Ponto getPontoAtual() {
        return pontoAtual;
    }

    public void setPontoAtual(Ponto pontoAtual) {
        this.pontoAtual = Objects.requireNonNull(pontoAtual, "Ponto atual do veículo não pode ser nulo.");
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = Objects.requireNonNull(status, "Status do veículo não pode ser nulo.");
    }

    @Override
    public String toString() {
        return String.format("Veiculo{id=%d, placa/nome='%s', pontoAtual=%s, status=%s}",
                id, placaOuNome, pontoAtual, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}