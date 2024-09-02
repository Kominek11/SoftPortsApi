package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaResponse;

public interface IncluirComentarioTarefa {

    TarefaResponse executar(Long id, String conteudo, Long usuarioId);
}
