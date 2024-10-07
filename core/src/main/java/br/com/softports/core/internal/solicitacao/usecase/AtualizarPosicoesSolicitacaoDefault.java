package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoPosicaoResponse;
import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.AtualizarPosicoesSolicitacao;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoPosicaoResponse;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarPosicoesTarefa;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaPosicaoResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.solicitacao.expression.SolicitacaoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AtualizarPosicoesSolicitacaoDefault implements AtualizarPosicoesSolicitacao {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoToSolicitacaoPosicaoResponse solicitacaoToSolicitacaoPosicaoResponse;

    @Override
    public List<SolicitacaoPosicaoResponse> executar(List<SolicitacaoPosicaoResponse> solicitacoes) {
        List<Solicitacao> solicitacoesList = new ArrayList<>();
        solicitacoes.forEach(item -> {
            BooleanBuilder filtro = new BooleanBuilder()
                    .and(SolicitacaoExpressions.id(item.id()));
            Solicitacao solicitacao = solicitacaoRepository.buscar(filtro).orElseThrow();
            solicitacao.setStatus(item.status());
            solicitacao.setPosicao(item.posicao());
            solicitacoesList.add(solicitacao);
        });
        solicitacaoRepository.salvarTodos(solicitacoesList);
        return solicitacoesList.stream()
                .map(solicitacaoToSolicitacaoPosicaoResponse::executar)
                .collect(Collectors.toList());
    }
}