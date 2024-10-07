package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;

public interface AtualizarFechadoSolicitacao {

    SolicitacaoResponse executar(Long id, Boolean fechado);
}
