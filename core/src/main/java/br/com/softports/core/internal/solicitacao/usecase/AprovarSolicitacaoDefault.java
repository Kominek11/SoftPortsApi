package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.AprovarSolicitacao;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.solicitacao.expression.SolicitacaoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class AprovarSolicitacaoDefault implements AprovarSolicitacao {

    private final SolicitacaoRepository solicitacaoRepository;
    private final TarefaRepository tarefaRepository;
    private final TarefaToTarefaResponse tarefaToTarefaResponse;

    @Override
    public TarefaResponse executar(Long solicitacaoId) {
        BooleanBuilder filtroSolicitacao = new BooleanBuilder().and(SolicitacaoExpressions.id(solicitacaoId));
        Solicitacao solicitacao = solicitacaoRepository.buscar(filtroSolicitacao).orElseThrow();
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(solicitacao.getTitulo());
        tarefa.setDescricao(solicitacao.getDescricao());
        tarefa.setSo(solicitacao.getSo());
        tarefa.setScreenshots(solicitacao.getScreenshots());
        tarefa.setCaminho(solicitacao.getCaminho());
        tarefa.setDataFechamento(solicitacao.getDataFechamento());
        tarefa.setDataCriacao(solicitacao.getDataCriacao());
        tarefa.setDataEstimada(solicitacao.getDataEstimada());
        tarefa.setStatus(solicitacao.getStatus());
        tarefa.setFechada(solicitacao.getFechada());
        tarefa.setPosicao(solicitacao.getPosicao());
        tarefa.setPrioridade(solicitacao.getPrioridade());
        tarefa.setProjeto(solicitacao.getProjeto());
        tarefa.setFeedback(solicitacao.getFeedback());
        Set<Usuario> usuariosCopy = new HashSet<>(solicitacao.getUsuarios());
        tarefa.setUsuarios(usuariosCopy);
        tarefa.setClassificacao(solicitacao.getClassificacao());
        tarefaRepository.salvar(tarefa);
        solicitacaoRepository.apagar(solicitacao);
        return tarefaToTarefaResponse.executar(tarefa);
    }
}