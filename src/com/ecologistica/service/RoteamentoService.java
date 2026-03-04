package com.ecologistica.service;

import com.ecologistica.domain.Rota;
import com.ecologistica.exception.BusinessException;

public interface RoteamentoService {
    Rota gerarRotaOtimizada(Long veiculoId, Long depositoId) throws BusinessException;
}