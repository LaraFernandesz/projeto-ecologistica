package com.ecologistica.app;

import com.ecologistica.domain.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1) Teste Ponto.distanceTo: (0,0) -> (3,4) = 5
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(3, 4);
        System.out.println("Distância (0,0) -> (3,4): " + a.distanceTo(b)); // esperado: 5.0

        // 2) Montar cenário de rota
        Deposito deposito = new Deposito(1L, "Central", new Ponto(0, 0));
        Veiculo veiculo = new Veiculo(10L, "VAN-01", deposito.getPonto());

        Entrega e1 = new Entrega(101L, "Cliente A", new Ponto(3, 4)); // dist = 5
        Entrega e2 = new Entrega(102L, "Cliente B", new Ponto(6, 8)); // dist e1->e2 = 5

        Rota rota = new Rota(1000L, deposito, veiculo, List.of(e1, e2));

        System.out.println(rota);
        System.out.println("Distância total da rota: " + rota.getDistanciaTotal());
        // esperado (com esses pontos):
        // deposito->e1 = 5
        // e1->e2 = 5
        // e2->deposito = 10
        // total = 20
    }
}