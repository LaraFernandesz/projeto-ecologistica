package com.ecologistica.service;

import com.ecologistica.domain.Deposito;
import com.ecologistica.domain.Entrega;
import com.ecologistica.domain.Ponto;
import com.ecologistica.domain.Rota;
import com.ecologistica.domain.Veiculo;
import com.ecologistica.domain.enums.StatusEntrega;
import com.ecologistica.domain.enums.StatusVeiculo;
import com.ecologistica.exception.BusinessException;
import com.ecologistica.exception.DepositoNaoEncontradoException;
import com.ecologistica.exception.SemEntregasPendentesException;
import com.ecologistica.exception.ValidationException;
import com.ecologistica.exception.VeiculoNaoEncontradoException;
import com.ecologistica.exception.VeiculoOcupadoException;
import com.ecologistica.repository.DepositoRepository;
import com.ecologistica.repository.EntregaRepository;
import com.ecologistica.repository.RotaRepository;
import com.ecologistica.repository.VeiculoRepository;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<Entrega> naoVisitadas = new ArrayList<>(entregasPendentes);

        Ponto pontoAtual = deposito.getPonto();

        while (!naoVisitadas.isEmpty()) {
            final Ponto refPonto = pontoAtual;

            Entrega maisProxima = naoVisitadas.stream()
                    .min(Comparator.comparingDouble(e -> refPonto.distanceTo(e.getPonto())))
                    .orElseThrow(() -> new ValidationException("Erro ao processar a próxima entrega."));

            rota.adicionarParada(maisProxima);
            maisProxima.setStatus(StatusEntrega.EM_ROTA);

            pontoAtual = maisProxima.getPonto();
            naoVisitadas.remove(maisProxima);
        }

        veiculo.setStatus(StatusVeiculo.OCUPADO);
        rota.recalcularDistanciaTotal();

        rotaRepository.save(rota);
        return rota;
    }
}