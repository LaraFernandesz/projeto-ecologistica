package com.ecologistica.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa uma rota logística realizada por um veículo.
 * A rota é um ciclo fechado (sai do depósito e retorna ao depósito).
 */
public class Rota {

    private final Long id;
    private final Deposito deposito;
    private final Veiculo veiculo;
    private final List<Entrega> listaParadas;

    private double distanciaTotal;

    public Rota(Long id, Deposito deposito, Veiculo veiculo, List<Entrega> listaParadas) {

        if (id == null) {
            throw new IllegalArgumentException("ID da rota não pode ser nulo.");
        }

        this.id = id;
        this.deposito = Objects.requireNonNull(deposito, "Depósito não pode ser nulo.");
        this.veiculo = Objects.requireNonNull(veiculo, "Veículo não pode ser nulo.");
        this.listaParadas = new ArrayList<>(Objects.requireNonNull(listaParadas, "Lista de paradas não pode ser nula."));

        recalcularDistanciaTotal();
    }

    public Long getId() {
        return id;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public List<Entrega> getListaParadas() {
        return Collections.unmodifiableList(listaParadas);
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    /**
     * Recalcula a distância total da rota considerando:
     * Depósito → entregas → retorno ao depósito.
     */
    public void recalcularDistanciaTotal() {

        if (listaParadas.isEmpty()) {
            distanciaTotal = 0.0;
            return;
        }

        double total = 0.0;

        Ponto pontoAtual = deposito.getPonto();

        // Percorre todas as entregas
        for (Entrega entrega : listaParadas) {
            total += pontoAtual.distanceTo(entrega.getPonto());
            pontoAtual = entrega.getPonto();
        }

        // Retorno ao depósito (fecha o ciclo)
        total += pontoAtual.distanceTo(deposito.getPonto());

        this.distanciaTotal = total;
    }

    @Override
    public String toString() {
        return String.format(
                "Rota{id=%d, deposito=%s, veiculo=%s, paradas=%d, distanciaTotal=%.2f}",
                id, deposito.getNome(), veiculo.getPlacaOuNome(),
                listaParadas.size(), distanciaTotal
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rota)) return false;
        Rota rota = (Rota) o;
        return Objects.equals(id, rota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}