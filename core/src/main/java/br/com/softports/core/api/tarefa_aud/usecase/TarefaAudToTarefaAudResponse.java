package br.com.softports.core.api.tarefa_aud.usecase;

import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.internal.common.entity.audited.TarefaAud;

public interface TarefaAudToTarefaAudResponse {

    TarefaAudResponse executar(TarefaAud tarefaAud);

}
