package br.com.softports.application.resources.dto.solicitacao;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoPosicaoResponse;

import java.util.List;

public record AtualizarPosicoesSolicitacaoRequest(
        List<SolicitacaoPosicaoResponse> solicitacoes
) { }
