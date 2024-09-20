package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.internal.common.entity.Tarefa;

public interface TarefaToTarefaResponse {

    TarefaResponse executar(Tarefa tarefa);

}
