package br.com.softports.core.api.regra.usecase;

import br.com.softports.core.api.regra.dto.AtualizarSituacaoRegraResponse;

public interface AtualizarSituacaoRegra {

    AtualizarSituacaoRegraResponse executar(Long id,
                                            String situacao);
    }
