package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

public interface BuscarTarefas {

    Pagina<TarefaResponse> executar(Integer tamanhoPagina,
                                    Integer numeroPagina,
                                    String ordenadoPor,
                                    String direcao);

    TarefaResponse executar(Long id);
}
