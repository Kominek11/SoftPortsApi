package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;

public interface SolicitacaoToSolicitacaoResponse {

    SolicitacaoResponse executar(Solicitacao solicitacao);
}
