package com.ecologistica.domain;

import java.util.ArrayList;
import java.util.List;

public class Rota {

    private Long id;
    private Deposito deposito;
    private Veiculo veiculo;
    private List<Entrega> listaParadas;
    private double distanciaTotal;

    public Rota(Long id, Deposito deposito, Veiculo veiculo) {
        this.id = id;
        this.deposito = deposito;
        this.veiculo = veiculo;
        this.listaParadas = new ArrayList<>();
        this.distanciaTotal = 0.0;
    }

    public void adicionarParada(Entrega entrega) {
        this.listaParadas.add(entrega);
    }

    public void recalcularDistanciaTotal() {

        if (listaParadas.isEmpty()) {
            distanciaTotal = 0;
            return;
        }

        double distancia = 0;

        Ponto pontoAtual = deposito.getPonto();

        for (Entrega entrega : listaParadas) {
            distancia += pontoAtual.distanceTo(entrega.getPonto());
            pontoAtual = entrega.getPonto();
        }

        distancia += pontoAtual.distanceTo(deposito.getPonto());

        this.distanciaTotal = distancia;
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
        return listaParadas;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    @Override
    public String toString() {
        return "Rota{id=" + id +
                ", deposito=" + deposito.getNome() +
                ", veiculo=" + veiculo.getPlaca() +
                ", paradas=" + listaParadas.size() +
                ", distanciaTotal=" + distanciaTotal +
                '}';
    }
}