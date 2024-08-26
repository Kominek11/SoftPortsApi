package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.util.Date;

public interface BuscarTarefas {

    Pagina<TarefaResponse> executar(Integer tamanhoPagina,
                                    Integer numeroPagina,
                                    String ordenadoPor,
                                    String direcao,
                                    Long projetoId,
                                    Boolean fechada,
                                    Date dataCriacao,
                                    Date dataCorrecao);

    TarefaResponse executar(Long id);
}
