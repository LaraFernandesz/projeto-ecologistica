package com.ecologistica.domain;

import java.util.ArrayList;
import java.util.List;

public class Rota {
    private Long id;
    private List<Entrega> paradas;
    private double distanciaTotal;

    public Rota(Long id) {
        this.id = id;
        this.paradas = new ArrayList<>();
        this.distanciaTotal = 0.0;
    }

    public void adicionarParada(Entrega entrega) {
        this.paradas.add(entrega);
    }

    public Long getId() { return id; }
    public List<Entrega> getParadas() { return paradas; }
    public double getDistanciaTotal() { return distanciaTotal; }
    public void setDistanciaTotal(double distanciaTotal) { this.distanciaTotal = distanciaTotal; }
}