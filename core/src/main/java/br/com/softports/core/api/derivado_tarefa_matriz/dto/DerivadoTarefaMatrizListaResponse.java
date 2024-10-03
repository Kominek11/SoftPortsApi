package br.com.softports.core.api.derivado_tarefa_matriz.dto;

import lombok.Builder;

@Builder
public record DerivadoTarefaMatrizListaResponse(
        Long id,
        Long derivadoId,
        Long tarefaId,
        Boolean valor,
        Long projetoId
) {}
