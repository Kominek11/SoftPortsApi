package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.AtualizarFeedbackSolicitacao;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.solicitacao.expression.SolicitacaoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarFeedbackSolicitacaoDefault implements AtualizarFeedbackSolicitacao {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse;

    @Override
    public SolicitacaoResponse executar(Long id, String feedback) {
        BooleanBuilder filtroSolicitacao = new BooleanBuilder().and(SolicitacaoExpressions.id(id));
        Solicitacao solicitacao = solicitacaoRepository.buscar(filtroSolicitacao).orElseThrow();
        solicitacao.setFeedback(feedback);
        solicitacaoRepository.salvar(solicitacao);
        return solicitacaoToSolicitacaoResponse.executar(solicitacao);
    }

}