package br.com.softports.application.resources.dto.solicitacao;

import java.util.Date;
import java.util.List;

public record CriarSolicitacaoRequest(
        String titulo,
        String descricao,
        String so,
        String caminho,
        Date dataEstimada,
        Long status,
        Long projetoId,
        List<Long> usuarioIds,
        byte[][] screenshots,
        Long classificacaoId,
        Long subclassificacaoId,
        Long prioridade
) { }
