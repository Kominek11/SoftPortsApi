package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;

public interface AtualizarFeedbackSolicitacao {

    SolicitacaoResponse executar(Long id, String feedback);
}
