package com.ecologistica.repository;

import com.ecologistica.domain.*;
import com.ecologistica.domain.enums.StatusEntrega;
import com.ecologistica.domain.enums.StatusVeiculo;

import java.util.List;

public class SeedData {

    public static void seed(InMemoryDatabase db) {

        if (!db.getDepositos().isEmpty()) {
            return;
        }

        Deposito deposito = new Deposito(1L, "Deposito Central", new Ponto(0,0));
        db.getDepositos().put(deposito.getId(), deposito);

        Veiculo veiculo = new Veiculo(1L, "ABC-1234", deposito.getPonto());
        veiculo.setStatus(StatusVeiculo.LIVRE);
        db.getVeiculos().put(veiculo.getId(), veiculo);

        List<Entrega> entregas = List.of(
                new Entrega(1L, "Cliente A", new Ponto(2,1), StatusEntrega.PENDENTE, null),
                new Entrega(2L, "Cliente B", new Ponto(5,3), StatusEntrega.PENDENTE, 10.0),
                new Entrega(3L, "Cliente C", new Ponto(1,4), StatusEntrega.EM_ROTA, null),
                new Entrega(4L, "Cliente D", new Ponto(6,2), StatusEntrega.PENDENTE, 20.0),
                new Entrega(5L, "Cliente E", new Ponto(-2,3), StatusEntrega.ENTREGUE, null),
                new Entrega(6L, "Cliente F", new Ponto(-4,-1), StatusEntrega.PENDENTE, null),
                new Entrega(7L, "Cliente G", new Ponto(3,-3), StatusEntrega.ENTREGUE, null),
                new Entrega(8L, "Cliente H", new Ponto(7,-2), StatusEntrega.PENDENTE, 35.0)
        );

        for (Entrega e : entregas) {
            db.getEntregas().put(e.getId(), e);
        }
    }
}