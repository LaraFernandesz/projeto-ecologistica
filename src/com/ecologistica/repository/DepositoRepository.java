package com.ecologistica.repository;

import com.ecologistica.domain.Deposito;

import java.util.ArrayList;
import java.util.List;

public class DepositoRepository {

    private final InMemoryDatabase db;

    public DepositoRepository(InMemoryDatabase db) {
        this.db = db;
    }

    public List<Deposito> findAll() {
        return new ArrayList<>(db.getDepositos().values());
    }

    public Deposito findById(Long id) {
        return db.getDepositos().get(id);
    }

    public void save(Deposito deposito) {
        db.getDepositos().put(deposito.getId(), deposito);
    }

    public void deleteById(Long id) {
        db.getDepositos().remove(id);
    }
}