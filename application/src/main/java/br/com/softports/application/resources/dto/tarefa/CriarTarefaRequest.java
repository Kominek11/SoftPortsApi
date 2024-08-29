package br.com.softports.application.resources.dto.tarefa;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

public record CriarTarefaRequest(
        String titulo,
        String descricao,
        String so,
        String caminho,
        Date dataEstimada,
        Long status,
        Long projetoId,
        Long posicao,
        List<Long> usuarioIds,
        byte[][] screenshots,
        Long classificacaoId,
        Long subclassificacaoId,
        Long prioridade
) { }
