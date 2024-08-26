package br.com.softports.core.api.tarefa.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;

import java.util.Date;
import java.util.List;

public interface CriarTarefa {

    TarefaResponse executar(String titulo, String descricao,
                            String so, String caminho,
                            Date dataCorrecao, Date dataCriacao,
                            Long prioridade, Long classificacao,
                            Long status, Boolean fechada,
                            Long posicao, Long projetoId,
                            Long usuarioId, String screenshots);

    TarefaResponse executar(String titulo, String descricao,
                            String so, String caminho,
                            Date dataCorrecao, Date dataCriacao,
                            Long prioridade, Long classificacao,
                            Long status, Boolean fechada,
                            Long posicao, Long projetoId,
                            List<Long> usuarioIds, String screenshots);
}
