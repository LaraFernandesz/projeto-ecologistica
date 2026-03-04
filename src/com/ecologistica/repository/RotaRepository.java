package com.ecologistica.repository;

import com.ecologistica.domain.Rota;

import java.util.ArrayList;
import java.util.List;

public class RotaRepository {

    private final InMemoryDatabase db;

    public RotaRepository(InMemoryDatabase db) {
        this.db = db;
    }

    public List<Rota> findAll() {
        return new ArrayList<>(db.getRotas().values());
    }

    public Rota findById(Long id) {
        return db.getRotas().get(id);
    }

    public void save(Rota rota) {
        db.getRotas().put(rota.getId(), rota);
    }

    public void deleteById(Long id) {
        db.getRotas().remove(id);
    }
}