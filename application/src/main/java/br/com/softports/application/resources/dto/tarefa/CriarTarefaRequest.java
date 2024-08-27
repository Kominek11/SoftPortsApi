package br.com.softports.application.resources.dto.tarefa;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public record CriarTarefaRequest(
        String titulo,
        String descricao,
        String so,
        String caminho,
        Date dataEstimada,
        Long prioridade,
        Long classificacao,
        Long status,
        Long projetoId,
        Boolean fechada,
        Long posicao,
        List<Long> usuarioIds,
        byte[] screenshots
) { }
