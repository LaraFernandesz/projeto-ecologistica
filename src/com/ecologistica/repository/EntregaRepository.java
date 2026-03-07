package com.ecologistica.repository;

import com.ecologistica.domain.Entrega;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EntregaRepository {

    private final InMemoryDatabase db;

    public EntregaRepository(InMemoryDatabase db) {
        this.db = db;
    }

    public List<Entrega> findAll() {
        return new ArrayList<>(db.getEntregas().values());
    }

    // Stream #1 (ordenado)
    public List<Entrega> findAllSortedById() {
        return db.getEntregas().values().stream()
                .sorted(Comparator.comparing(Entrega::getId))
                .collect(Collectors.toList());
    }

    public Entrega findById(Long id) {
        return db.getEntregas().get(id);
    }

    public void save(Entrega entrega) {
        db.getEntregas().put(entrega.getId(), entrega);
    }

    public void deleteById(Long id) {
        db.getEntregas().remove(id);
    }

    // Stream #2
    public List<Entrega> findPendentes() {
        return db.getEntregas().values().stream()
                .filter(e -> e.getStatus() != null)
                .filter(e -> e.getStatus().equalsIgnoreCase("PENDENTE"))
                .collect(Collectors.toList());
    }

    public List<Entrega> findByStatus(String status) {
        return db.getEntregas().values().stream()
                .filter(e -> e.getStatus() != null)
                .filter(e -> e.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}