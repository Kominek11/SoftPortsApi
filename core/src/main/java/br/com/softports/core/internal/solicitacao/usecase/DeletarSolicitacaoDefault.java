package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.DeletarSolicitacao;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.solicitacao.expression.SolicitacaoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeletarSolicitacaoDefault implements DeletarSolicitacao {

    private final SolicitacaoRepository solicitacaoRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(SolicitacaoExpressions.id(id));
        Solicitacao solicitacao = solicitacaoRepository.buscar(filtro).orElseThrow();
        solicitacaoRepository.apagar(solicitacao);
    }
}