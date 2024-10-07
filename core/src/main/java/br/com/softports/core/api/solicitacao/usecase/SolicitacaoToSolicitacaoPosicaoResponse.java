package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoPosicaoResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;

public interface SolicitacaoToSolicitacaoPosicaoResponse {

    SolicitacaoPosicaoResponse executar(Solicitacao solicitacao);

}
