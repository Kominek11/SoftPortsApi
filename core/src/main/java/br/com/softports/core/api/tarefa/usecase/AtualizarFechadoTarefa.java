package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaResponse;

public interface AtualizarFechadoTarefa {

    TarefaResponse executar(Long id, Boolean fechado);
}
