package br.com.softports.application.resources.dto.derivado_tarefa_matriz;

import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizListaResponse;

import java.util.List;

public record CriarDerivadoTarefaMatrizListaRequest(
        List<DerivadoTarefaMatrizListaResponse> derivadoTarefaMatrizListaResponses
) { }
