package com.ecologistica.app;

import com.ecologistica.repository.DepositoRepository;
import com.ecologistica.repository.EntregaRepository;
import com.ecologistica.repository.InMemoryDatabase;
import com.ecologistica.repository.RotaRepository;
import com.ecologistica.repository.SeedData;
import com.ecologistica.repository.VeiculoRepository;
import com.ecologistica.service.RoteamentoService;
import com.ecologistica.service.RoteamentoServiceImpl;
import com.ecologistica.ui.Menu;

public class Main {
    public static void main(String[] args) {

        InMemoryDatabase db = new InMemoryDatabase();
        SeedData.seed(db);

        VeiculoRepository veiculoRepository = new VeiculoRepository(db);
        DepositoRepository depositoRepository = new DepositoRepository(db);
        EntregaRepository entregaRepository = new EntregaRepository(db);
        RotaRepository rotaRepository = new RotaRepository(db);

        RoteamentoService service = new RoteamentoServiceImpl(
                veiculoRepository,
                depositoRepository,
                entregaRepository,
                rotaRepository
        );

        Menu menu = new Menu(service);

        System.out.println("EcoLogística inicializada!");
        menu.exibir();
    }
}