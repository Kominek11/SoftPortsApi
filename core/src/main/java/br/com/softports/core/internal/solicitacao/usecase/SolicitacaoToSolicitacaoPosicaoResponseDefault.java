package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoPosicaoResponse;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoPosicaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaPosicaoResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.common.entity.Tarefa;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SolicitacaoToSolicitacaoPosicaoResponseDefault implements SolicitacaoToSolicitacaoPosicaoResponse {

    @Override
    public SolicitacaoPosicaoResponse executar(Solicitacao solicitacao) {
        return SolicitacaoPosicaoResponse.builder()
                .id(solicitacao.getId())
                .status(solicitacao.getStatus())
                .posicao(solicitacao.getPosicao())
                .build();
    }
}
