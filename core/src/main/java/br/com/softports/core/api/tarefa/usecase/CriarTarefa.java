package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CriarTarefa {

    TarefaResponse executar(String titulo, String descricao,
                            String so, String caminho, Date dataEstimada,
                            Long prioridade,
                            Long status,
                            Long posicao, Long projetoId,
                            Long usuarioId, byte[][] screenshots,
                            Set<Long> classificacoes);

    TarefaResponse executar(String titulo, String descricao,
                            String so, String caminho, Date dataEstimada,
                            Long prioridade,
                            Long status,
                            Long posicao, Long projetoId,
                            List<Long> usuarioIds, byte[][] screenshots,
                            Set<Long> classificacoes);
}
