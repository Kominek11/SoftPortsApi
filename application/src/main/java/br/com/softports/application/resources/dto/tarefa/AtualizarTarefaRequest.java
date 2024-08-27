package br.com.softports.application.resources.dto.tarefa;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public record AtualizarTarefaRequest(
        Long id,
        String titulo,
        String descricao,
        String so,
        String caminho,
        Date dataFechamento,
        Date dataEstimada,
        Long prioridade,
        Long classificacao,
        Long status,
        Boolean fechada,
        Long posicao,
        Long projetoId,
        List<Long> usuarioIds,
        byte[] screenshots
) { }
