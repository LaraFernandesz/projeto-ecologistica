package com.ecologistica.repository;

import com.ecologistica.domain.Deposito;

import java.util.Optional;

public interface DepositoRepository {
    Optional<Deposito> getPrincipal();
}