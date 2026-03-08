package com.ecologistica.repository;

import com.ecologistica.domain.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    private final InMemoryDatabase db;

    public VeiculoRepository(InMemoryDatabase db) {
        this.db = db;
    }

    public List<Veiculo> findAll() {
        return new ArrayList<>(db.getVeiculos().values());
    }

    public Veiculo findById(Long id) {
        return db.getVeiculos().get(id);
    }

    public void save(Veiculo veiculo) {
        db.getVeiculos().put(veiculo.getId(), veiculo);
    }

    public void deleteById(Long id) {
        db.getVeiculos().remove(id);
    }
}