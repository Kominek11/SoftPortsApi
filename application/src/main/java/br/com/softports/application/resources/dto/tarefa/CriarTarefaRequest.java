package br.com.softports.application.resources.dto.tarefa;

import java.util.Date;
import java.util.List;

public record CriarTarefaRequest(
        String titulo,
        String descricao,
        String so,
        String caminho,
        Date dataCorrecao,
        Date dataCriacao,
        Long prioridade,
        Long classificacao,
        Long status,
        Long projetoId,
        List<Long> usuarioIds
) { }
