package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.tarefa.dto.TarefaResponse;

public interface AprovarSolicitacao {

    TarefaResponse executar(Long solicitacaoId);
}
