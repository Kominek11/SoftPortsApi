package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface AtualizarTarefa {

    TarefaResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho,
                            Date dataEstimada,
                            Long status,
                            Boolean fechada, Long posicao,
                            Long projetoId, Long usuarioId,
                            byte[][] screenshots, Long classificacaoId,
                            Long subclassificacaoId,
                            Long prioridade);

    TarefaResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho,
                            Date dataEstimada,
                            Long status,
                            Boolean fechada, Long posicao,
                            Long projetoId, List<Long> usuarioIds,
                            byte[][] screenshots, Long classificacaoId,
                            Long subclassificacaoId,
                            Long prioridade);
}
