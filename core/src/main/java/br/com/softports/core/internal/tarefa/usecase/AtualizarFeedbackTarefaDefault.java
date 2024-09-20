package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarFeedbackTarefa;
import br.com.softports.core.api.tarefa.usecase.AtualizarStatusTarefa;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AtualizarFeedbackTarefaDefault implements AtualizarFeedbackTarefa {

    private final TarefaRepository tarefaRepository;
    private final TarefaToTarefaResponse tarefaToTarefaResponse;

    @Override
    public TarefaResponse executar(Long id, String feedback) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        tarefa.setFeedback(feedback);
        tarefaRepository.salvar(tarefa);
        return tarefaToTarefaResponse.executar(tarefa);
    }

}