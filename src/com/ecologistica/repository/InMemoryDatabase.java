package com.ecologistica.repository;

import com.ecologistica.domain.*;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase {

    private final Map<Long, Deposito> depositos = new HashMap<>();
    private final Map<Long, Veiculo> veiculos = new HashMap<>();
    private final Map<Long, Entrega> entregas = new HashMap<>();
    private final Map<Long, Rota> rotas = new HashMap<>();

    public Map<Long, Deposito> getDepositos() { return depositos; }
    public Map<Long, Veiculo> getVeiculos() { return veiculos; }
    public Map<Long, Entrega> getEntregas() { return entregas; }
    public Map<Long, Rota> getRotas() { return rotas; }

    public void clearAll() {
        depositos.clear();
        veiculos.clear();
        entregas.clear();
        rotas.clear();
    }
}