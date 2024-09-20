package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarPosicoesTarefa;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaPosicaoResponse;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AtualizarPosicoesTarefaDefault implements AtualizarPosicoesTarefa {

    private final TarefaRepository tarefaRepository;
    private final TarefaToTarefaPosicaoResponse tarefaToTarefaPosicaoResponse;

    @Override
    public List<TarefaPosicaoResponse> executar(List<TarefaPosicaoResponse> tarefas) {
        List<Tarefa> tarefasList = new ArrayList<>();
        tarefas.forEach(item -> {
            BooleanBuilder filtro = new BooleanBuilder()
                    .and(TarefaExpressions.id(item.id()));
            Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
            tarefa.setStatus(item.status());
            tarefa.setPosicao(item.posicao());
            tarefasList.add(tarefa);
        });
        tarefaRepository.salvarTodos(tarefasList);
        return tarefasList.stream()
                .map(tarefaToTarefaPosicaoResponse::executar)
                .collect(Collectors.toList());
    }
}