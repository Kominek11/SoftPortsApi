package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.internal.common.entity.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BuscarTarefas {

    Pagina<TarefaResponse> executar(Integer tamanhoPagina,
                                    Integer numeroPagina,
                                    String ordenadoPor,
                                    String direcao,
                                    Long projetoId,
                                    Boolean fechada,
                                    LocalDate dataInicio,
                                    LocalDate dataFim,
                                    String titulo,
                                    Set<Long> usuarios,
                                    List<Long> prioridades,
                                    List<Long> classificacao,
                                    List<Long> subClassificacao);

    TarefaResponse executar(Long id);
}
