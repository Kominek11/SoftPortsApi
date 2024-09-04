package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;

import java.util.List;

public interface AtualizarPosicoesTarefa {

    List<TarefaPosicaoResponse> executar(List<TarefaPosicaoResponse> tarefas);
}
