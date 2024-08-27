package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaResponse;

public interface AtualizarFeedbackTarefa {

    TarefaResponse executar(Long id, String feedback);
}
