package com.ecologistica.app;

import com.ecologistica.service.RoteamentoService;
import com.ecologistica.service.RoteamentoServiceImpl;
import com.ecologistica.ui.Menu;

public class Main {
    public static void main(String[] args) {
        RoteamentoService service = new RoteamentoServiceImpl();

        Menu menu = new Menu(service);

        System.out.println("EcoLogística inicializada!");
        menu.exibir();
    }
}