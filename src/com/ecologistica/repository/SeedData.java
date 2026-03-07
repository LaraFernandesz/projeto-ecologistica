package com.ecologistica.repository;

import com.ecologistica.domain.Deposito;
import com.ecologistica.domain.Entrega;
import com.ecologistica.domain.Ponto;
import com.ecologistica.domain.Veiculo;
import com.ecologistica.domain.enums.StatusEntrega;
import com.ecologistica.domain.enums.StatusVeiculo;

import java.util.List;

public class SeedData {

    public static void seed(InMemoryDatabase db) {

        if (!db.getDepositos().isEmpty()) {
            return;
        }

        Deposito deposito = new Deposito(1L, "Deposito Central", new Ponto(0, 0));
        db.getDepositos().put(deposito.getId(), deposito);

        Veiculo veiculo = new Veiculo(1L, "ABC-1234", deposito.getPonto());
        veiculo.setStatus(StatusVeiculo.LIVRE);
        db.getVeiculos().put(veiculo.getId(), veiculo);

        List<Entrega> entregas = List.of(
                new Entrega(1L, "Entrega 1", new Ponto(2, 1), StatusEntrega.PENDENTE),
                new Entrega(2L, "Entrega 2", new Ponto(5, 3), StatusEntrega.PENDENTE),
                new Entrega(3L, "Entrega 3", new Ponto(1, 4), StatusEntrega.EM_ROTA),
                new Entrega(4L, "Entrega 4", new Ponto(6, 2), StatusEntrega.PENDENTE),
                new Entrega(5L, "Entrega 5", new Ponto(-2, 3), StatusEntrega.ENTREGUE),
                new Entrega(6L, "Entrega 6", new Ponto(-4, -1), StatusEntrega.PENDENTE),
                new Entrega(7L, "Entrega 7", new Ponto(3, -3), StatusEntrega.ENTREGUE),
                new Entrega(8L, "Entrega 8", new Ponto(7, -2), StatusEntrega.PENDENTE)
        );

        for (Entrega e : entregas) {
            db.getEntregas().put(e.getId(), e);
        }
    }
}