package br.com.softports.application.resources.dto.tarefa;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

public record AtualizarTarefaRequest(
        Long id,
        String titulo,
        String descricao,
        String so,
        String caminho,
        Date dataFechamento,
        Date dataEstimada,
        Long status,
        Boolean fechada,
        Long posicao,
        Long projetoId,
        List<Long> usuarioIds,
        byte[][] screenshots,
        Long classificacaoId,
        Long subclassificacaoId,
        Long prioridade
) { }
