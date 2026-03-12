package com.ecologistica.ui;

import com.ecologistica.domain.Entrega;
import com.ecologistica.exception.BusinessException;
import com.ecologistica.repository.InMemoryDatabase;
import com.ecologistica.repository.SeedData;
import com.ecologistica.service.RoteamentoService;

public class Menu {

    private final RoteamentoService roteamentoService;
    private final InMemoryDatabase database;

    public Menu(RoteamentoService roteamentoService, InMemoryDatabase database) {
        this.roteamentoService = roteamentoService;
        this.database = database;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA ECOLOGISTICA ===");
            System.out.println("1. Gerar/Carregar dados iniciais");
            System.out.println("2. Listar entidades");
            System.out.println("3. Executar algoritmo de roteamento");
            System.out.println("4. Exibir relatórios e estatísticas");
            System.out.println("5. Sair");

            opcao = ConsoleIO.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1: gerarDados(); break;
                case 2: listarEntidades(); break;
                case 3: executarRoteamento(); break;
                case 4: mostrarRelatorios(); break;
                case 5: ConsoleIO.aviso("Sistema encerrado."); break;
                default: ConsoleIO.erro("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void gerarDados() {
        try {
            SeedData.seed(database);
            ConsoleIO.sucesso("Dados iniciais carregados com sucesso.");
        } catch (Exception e) {
            ConsoleIO.erro("Erro ao gerar dados: " + e.getMessage());
        }
    }

    private void listarEntidades() {
        System.out.println("\n--- DEPÓSITOS ---");
        database.getDepositos().values().forEach(System.out::println);

        System.out.println("\n--- VEÍCULOS ---");
        database.getVeiculos().values().forEach(System.out::println);

        System.out.println("\n--- ENTREGAS ---");
        database.getEntregas().values().forEach(System.out::println);

        System.out.println("\n--- ROTAS ---");
        database.getRotas().values().forEach(System.out::println);
    }

    private void executarRoteamento() {
        try {
            long veiculoId = ConsoleIO.readInt("ID do veículo: ");
            long depositoId = ConsoleIO.readInt("ID do depósito: ");

            roteamentoService.gerarRotaOtimizada(veiculoId, depositoId);
            ConsoleIO.sucesso("Algoritmo executado e rota gerada!");

        } catch (BusinessException e) {
            ConsoleIO.erro(e.getMessage());
        } catch (Exception e) {
            ConsoleIO.erro("Erro inesperado: " + e.getMessage());
        }
    }

    private void mostrarRelatorios() {
        System.out.println("\n--- RELATÓRIO ---");

        long entregues = database.getEntregas().values().stream()
                .filter(e -> e.getStatus().name().equals("ENTREGUE"))
                .count();

        long pendentes = database.getEntregas().values().stream()
                .filter(e -> e.getStatus().name().equals("PENDENTE"))
                .count();

        System.out.println("Total de depósitos: " + database.getDepositos().size());
        System.out.println("Total de veículos: " + database.getVeiculos().size());
        System.out.println("Entregas Pendentes: " + pendentes);
        System.out.println("Entregas Concluídas: " + entregues);
        System.out.println("Total de rotas geradas: " + database.getRotas().size());
    }
}