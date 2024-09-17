package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;

public interface BuscarAuditoriaTarefa {

    Pagina<TarefaAudResponse> executar(Long tarefaId,
                                       Integer tamanhoPagina,
                                       Integer numeroPagina,
                                       String ordenadoPor,
                                       String direcao);
}
