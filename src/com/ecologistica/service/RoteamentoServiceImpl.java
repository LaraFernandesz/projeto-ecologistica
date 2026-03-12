package com.ecologistica.service;

import com.ecologistica.domain.*;
import com.ecologistica.domain.enums.StatusEntrega;
import com.ecologistica.domain.enums.StatusVeiculo;
import com.ecologistica.exception.*;
import com.ecologistica.repository.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RoteamentoServiceImpl implements RoteamentoService {

    private final VeiculoRepository veiculoRepository;
    private final DepositoRepository depositoRepository;
    private final EntregaRepository entregaRepository;
    private final RotaRepository rotaRepository;

    public RoteamentoServiceImpl(VeiculoRepository veiculoRepository,
                                 DepositoRepository depositoRepository,
                                 EntregaRepository entregaRepository,
                                 RotaRepository rotaRepository) {
        this.veiculoRepository = veiculoRepository;
        this.depositoRepository = depositoRepository;
        this.entregaRepository = entregaRepository;
        this.rotaRepository = rotaRepository;
    }

    @Override
    public Rota gerarRotaOtimizada(Long veiculoId, Long depositoId) throws BusinessException {

        Veiculo veiculo = veiculoRepository.findById(veiculoId);
        if (veiculo == null) {
            throw new VeiculoNaoEncontradoException("Veículo não encontrado.");
        }

        if (veiculo.getStatus() != StatusVeiculo.LIVRE) {
            throw new VeiculoOcupadoException("O veículo selecionado não está livre.");
        }

        Deposito deposito = depositoRepository.findById(depositoId);
        if (deposito == null) {
            throw new DepositoNaoEncontradoException("Depósito não encontrado.");
        }

        List<Entrega> entregasPendentes = entregaRepository.findPendentes();
        if (entregasPendentes == null || entregasPendentes.isEmpty()) {
            throw new SemEntregasPendentesException("Não existem entregas pendentes para roteamento.");
        }

        Rota rota = new Rota(System.currentTimeMillis(), deposito, veiculo);
        Deque<Entrega> filaPlanejada = new LinkedList<>(entregasPendentes);

        Ponto pontoAtual = deposito.getPonto();
        double tempoAtual = 0.0;

        while (!filaPlanejada.isEmpty()) {
            final Ponto refPonto = pontoAtual;
            final double refTempo = tempoAtual;

            Entrega escolhida = filaPlanejada.stream()
                    .filter(e -> e.getHorarioLimite() == null || (refTempo + refPonto.distanceTo(e.getPonto())) <= e.getHorarioLimite())
                    .min(Comparator.comparingDouble(e -> {
                        double distancia = refPonto.distanceTo(e.getPonto());
                        if (e.getHorarioLimite() == null) {
                            return distancia * 2;
                        }
                        double urgencia = e.getHorarioLimite() - (refTempo + distancia);
                        return distancia + urgencia;
                    }))
                    .orElseThrow(() -> new RotaInviavelException("Matematicamente impossível visitar todos os pontos sem violar uma janela de tempo."));

            if (!filaPlanejada.getFirst().equals(escolhida)) {
                filaPlanejada.remove(escolhida);
                filaPlanejada.addFirst(escolhida);
            }

            Entrega proxima = filaPlanejada.removeFirst();

            double distanciaPercorrida = refPonto.distanceTo(proxima.getPonto());
            tempoAtual += distanciaPercorrida;

            rota.adicionarParada(proxima);
            proxima.setStatus(StatusEntrega.EM_ROTA);
            pontoAtual = proxima.getPonto();
        }

        veiculo.setStatus(StatusVeiculo.OCUPADO);
        rota.recalcularDistanciaTotal();

        rotaRepository.save(rota);
        return rota;
    }
}