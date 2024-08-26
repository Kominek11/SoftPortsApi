package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.util.Date;
import java.util.List;

public interface AtualizarStatusTarefa {

    TarefaResponse executar(Long id, Long status);
}
