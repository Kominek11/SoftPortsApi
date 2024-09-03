package br.com.softports.application.resources.dto.tarefa;

import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;

import java.util.List;

public record AtualizarPosicoesTarefaRequest(
        List<TarefaPosicaoResponse> tarefas
) { }
