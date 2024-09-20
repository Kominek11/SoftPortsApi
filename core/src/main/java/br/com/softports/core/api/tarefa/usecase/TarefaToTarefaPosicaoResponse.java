package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.internal.common.entity.Tarefa;

public interface TarefaToTarefaPosicaoResponse {

    TarefaPosicaoResponse executar(Tarefa tarefa);

}
