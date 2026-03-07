package com.ecologistica.repository;

import com.ecologistica.domain.*;

import java.util.List;

public class SeedData {

    public static void seed(InMemoryDatabase db) {

        if (!db.getDepositos().isEmpty()) {
            return;
        }

        Deposito deposito = new Deposito(1L, "Deposito Central", new Ponto(0,0));
        db.getDepositos().put(deposito.getId(), deposito);

        Veiculo veiculo = new Veiculo(1L, "ABC-1234", deposito.getPonto());
        veiculo.setStatus("LIVRE");
        db.getVeiculos().put(veiculo.getId(), veiculo);

        List<Entrega> entregas = List.of(
                new Entrega(1L, new Ponto(2,1),"PENDENTE"),
                new Entrega(2L, new Ponto(5,3),"PENDENTE"),
                new Entrega(3L, new Ponto(1,4),"EM_ROTA"),
                new Entrega(4L, new Ponto(6,2),"PENDENTE"),
                new Entrega(5L, new Ponto(-2,3),"ENTREGUE"),
                new Entrega(6L, new Ponto(-4,-1),"PENDENTE"),
                new Entrega(7L, new Ponto(3,-3),"ENTREGUE"),
                new Entrega(8L, new Ponto(7,-2),"PENDENTE")
        );

        for (Entrega e : entregas) {
            db.getEntregas().put(e.getId(), e);
        }
    }
}