package com.ecologistica.app;

import com.ecologistica.repository.*;
import com.ecologistica.service.RoteamentoService;
import com.ecologistica.service.RoteamentoServiceImpl;
import com.ecologistica.ui.Menu;

public class Main {
    public static void main(String[] args) {
        InMemoryDatabase database = new InMemoryDatabase();

        VeiculoRepository veiculoRepo = new VeiculoRepository(database);
        DepositoRepository depositoRepo = new DepositoRepository(database);
        EntregaRepository entregaRepo = new EntregaRepository(database);
        RotaRepository rotaRepo = new RotaRepository(database);

        RoteamentoService service = new RoteamentoServiceImpl(
                veiculoRepo, depositoRepo, entregaRepo, rotaRepo);

        Menu menu = new Menu(service, database);

        System.out.println("EcoLogística inicializada com sucesso!");
        menu.exibir();
    }
}