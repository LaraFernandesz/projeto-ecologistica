package com.ecologistica.repository;

import com.ecologistica.domain.Entrega;
import com.ecologistica.domain.enums.StatusEntrega;

import java.util.List;

public interface EntregaRepository {
    List<Entrega> findAll();
    List<Entrega> findByStatus(StatusEntrega status);
    List<Entrega> findPendentes();
}