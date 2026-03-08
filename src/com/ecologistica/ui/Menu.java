package com.ecologistica.ui;

import com.ecologistica.service.RoteamentoService;

public class Menu {
    private final RoteamentoService roteamentoService;

    public Menu(RoteamentoService roteamentoService) {
        this.roteamentoService = roteamentoService;
    }

    public void exibir() {
        System.out.println("1. Gerar Rota");
    }

    private void executarRoteamento() {
        try {
            this.roteamentoService.gerarRotaOtimizada(1L, 1L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}