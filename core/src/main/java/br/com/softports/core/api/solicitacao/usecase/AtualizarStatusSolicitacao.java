package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;

public interface AtualizarStatusSolicitacao {

    SolicitacaoResponse executar(Long id, Long status);
}
