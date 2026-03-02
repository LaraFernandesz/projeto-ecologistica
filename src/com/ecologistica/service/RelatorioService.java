package com.ecologistica.service;

import com.ecologistica.domain.Deposito;
import com.ecologistica.domain.Entrega;
import com.ecologistica.domain.enums.StatusEntrega;
import com.ecologistica.repository.DepositoRepository;
import com.ecologistica.repository.EntregaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RelatorioService {

    private final EntregaRepository entregaRepository;
    private final DepositoRepository depositoRepository;

    public RelatorioService(EntregaRepository entregaRepository, DepositoRepository depositoRepository) {
        if (entregaRepository == null) throw new IllegalArgumentException("entregaRepository não pode ser null.");
        if (depositoRepository == null) throw new IllegalArgumentException("depositoRepository não pode ser null.");
        this.entregaRepository = entregaRepository;
        this.depositoRepository = depositoRepository;
    }

    public void imprimirTodos() {
        titulo("RELATÓRIOS — ECOLOGÍSTICA");

        relatorio1_totalPorStatus();
        linha();

        relatorio2_pendentesOrdenadasPorDistanciaAoDeposito();
        linha();

        relatorio3_top3MaisLongeDoDeposito();
        linha();

        relatorio4_distanciaMediaPendentes(); // opcional (se quiser, mantém)
        linha();
    }

    // Relatório 1: total por status (groupingBy)
    public void relatorio1_totalPorStatus() {
        titulo("RELATÓRIO 1 — Total de entregas por status");

        List<Entrega> entregas = entregaRepository.findAll();

        Map<StatusEntrega, Long> contagem = entregas.stream()
                .collect(Collectors.groupingBy(Entrega::getStatus, Collectors.counting()));

        if (contagem.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
            return;
        }

        // imprime numa ordem fixa para ficar bonito
        List.of(StatusEntrega.PENDENTE, StatusEntrega.EM_ROTA, StatusEntrega.ENTREGUE).stream()
                .filter(contagem::containsKey)
                .forEach(status -> System.out.printf("%-10s : %d%n", status, contagem.get(status)));
    }

    // Relatório 2: pendentes ordenadas por distância ao depósito (sorted)
    public void relatorio2_pendentesOrdenadasPorDistanciaAoDeposito() {
        titulo("RELATÓRIO 2 — Pendentes ordenadas por distância ao depósito");

        Optional<Deposito> depositoOpt = depositoRepository.getPrincipal();
        if (depositoOpt.isEmpty()) {
            System.out.println("Nenhum depósito cadastrado no sistema.");
            return;
        }
        Deposito deposito = depositoOpt.get();

        List<Entrega> pendentesOrdenadas = entregaRepository.findPendentes().stream()
                .sorted(Comparator.comparingDouble(e -> e.getPonto().distanceTo(deposito.getPonto())))
                .toList();

        if (pendentesOrdenadas.isEmpty()) {
            System.out.println("Não há entregas pendentes.");
            return;
        }

        pendentesOrdenadas.stream()
                .map(e -> formatEntregaComDistancia(deposito, e))
                .forEach(System.out::println);
    }

    // Relatório 3: top 3 mais longe (limit)
    public void relatorio3_top3MaisLongeDoDeposito() {
        titulo("RELATÓRIO 3 — Top 3 pendentes mais longe do depósito");

        Optional<Deposito> depositoOpt = depositoRepository.getPrincipal();
        if (depositoOpt.isEmpty()) {
            System.out.println("Nenhum depósito cadastrado no sistema.");
            return;
        }
        Deposito deposito = depositoOpt.get();

        List<Entrega> top3 = entregaRepository.findPendentes().stream()
                .sorted(Comparator.comparingDouble((Entrega e) -> e.getPonto().distanceTo(deposito.getPonto())).reversed())
                .limit(3)
                .toList();

        if (top3.isEmpty()) {
            System.out.println("Não há entregas pendentes.");
            return;
        }

        top3.stream()
                .map(e -> formatEntregaComDistancia(deposito, e))
                .forEach(System.out::println);
    }

    // Relatório 4 (opcional): distância média das pendentes
    public void relatorio4_distanciaMediaPendentes() {
        titulo("RELATÓRIO 4 — Distância média das entregas pendentes (opcional)");

        Optional<Deposito> depositoOpt = depositoRepository.getPrincipal();
        if (depositoOpt.isEmpty()) {
            System.out.println("Nenhum depósito cadastrado no sistema.");
            return;
        }
        Deposito deposito = depositoOpt.get();

        var avgOpt = entregaRepository.findPendentes().stream()
                .mapToDouble(e -> e.getPonto().distanceTo(deposito.getPonto()))
                .average();

        if (avgOpt.isEmpty()) {
            System.out.println("Não há entregas pendentes.");
            return;
        }

        System.out.printf("Distância média (pendentes): %.2f%n", avgOpt.getAsDouble());
    }

    // ===== helpers (formatação) =====

    private static String formatEntregaComDistancia(Deposito deposito, Entrega e) {
        double d = e.getPonto().distanceTo(deposito.getPonto());
        String id = (e.getId() == null) ? "s/ id" : String.valueOf(e.getId());
        String rotulo = (e.getClienteOuEndereco() == null || e.getClienteOuEndereco().isBlank())
                ? "Entrega " + id
                : "Entrega " + id + " (" + e.getClienteOuEndereco() + ")";
        return String.format("%s — dist: %.2f", rotulo, d);
    }

    private static void titulo(String t) {
        System.out.println("==== " + t + " ====");
    }

    private static void linha() {
        System.out.println();
    }
}