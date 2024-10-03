package br.com.softports.core.api.derivado_tarefa_matriz.dto;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import lombok.Builder;

@Builder
public record DerivadoTarefaMatrizResponse(
        Long id,
        DerivadoResponse derivadoResponse,
        TarefaResponse tarefaResponse,
        Boolean valor,
        ProjetoResponse projetoResponse
) {}
