package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoPosicaoResponse;

import java.util.List;

public interface AtualizarPosicoesSolicitacao {

    List<SolicitacaoPosicaoResponse> executar(List<SolicitacaoPosicaoResponse> solicitacoes);
}
