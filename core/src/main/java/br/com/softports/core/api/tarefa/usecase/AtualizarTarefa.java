package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.util.Date;
import java.util.List;

public interface AtualizarTarefa {

    TarefaResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho, Date dataCorrecao,
                            Date dataCriacao, Long prioridade,
                            Long classificacao, Long status,
                            Long projetoId, Long usuarioId);

    TarefaResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho, Date dataCorrecao,
                            Date dataCriacao, Long prioridade,
                            Long classificacao, Long status,
                            Long projetoId, List<Long> usuarioIds);
}
