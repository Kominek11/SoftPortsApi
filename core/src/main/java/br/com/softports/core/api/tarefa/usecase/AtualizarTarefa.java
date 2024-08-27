package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public interface AtualizarTarefa {

    TarefaResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho, Date dataFechamento,
                            Date dataEstimada, Long prioridade,
                            Long classificacao, Long status,
                            Boolean fechada, Long posicao,
                            Long projetoId, Long usuarioId,
                            Blob screenshots);

    TarefaResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho, Date dataFechamento,
                            Date dataEstimada, Long prioridade,
                            Long classificacao, Long status,
                            Boolean fechada, Long posicao,
                            Long projetoId, List<Long> usuarioIds,
                            Blob screenshots);
}
