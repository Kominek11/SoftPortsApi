package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.AtualizarSolicitacao;
import br.com.softports.core.api.solicitacao.usecase.AtualizarStatusSolicitacao;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarStatusTarefa;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.solicitacao.expression.SolicitacaoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class AtualizarStatusSolicitacaoDefault implements AtualizarStatusSolicitacao {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse;


    @Override
    public SolicitacaoResponse executar(Long id, Long status) {
        BooleanBuilder filtroSolicitacao = new BooleanBuilder().and(SolicitacaoExpressions.id(id));
        Solicitacao solicitacao = solicitacaoRepository.buscar(filtroSolicitacao).orElseThrow();
        solicitacao.setStatus(status);
        solicitacao.setDataFechamento(new Date());
        solicitacaoRepository.salvar(solicitacao);
        return solicitacaoToSolicitacaoResponse.executar(solicitacao);
    }
}