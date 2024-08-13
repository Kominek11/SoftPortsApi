package br.com.softports.core.api.tarefa.dto;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import lombok.Builder;

import java.util.Date;

@Builder
public record TarefaResponse(
        Long id,
        String descricao,
        String so,
        String caminho,
        Date dataCorrecao,
        Date dataCriacao,
        Long prioridade,
        Long classificacao,
        Long status,
        ProjetoResponse projeto
) {}
