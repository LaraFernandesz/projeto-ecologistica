package com.ecologistica.domain;

import java.util.Objects;

/**
 * Representa o depósito central da operação logística.
 */
public class Deposito {

    private final Long id;
    private final String nome;
    private final Ponto ponto;

    public Deposito(Long id, String nome, Ponto ponto) {
        if (id == null) {
            throw new IllegalArgumentException("ID do depósito não pode ser nulo.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do depósito não pode ser vazio.");
        }

        this.id = id;
        this.nome = nome;
        this.ponto = Objects.requireNonNull(ponto, "Ponto do depósito não pode ser nulo.");
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

    @Override
    public String toString() {
        return String.format("Deposito{id=%d, nome='%s', ponto=%s}", id, nome, ponto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deposito)) return false;
        Deposito deposito = (Deposito) o;
        return Objects.equals(id, deposito.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}