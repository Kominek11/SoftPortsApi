package br.com.softports.core.api.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizListaResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;

import java.util.List;

public interface CriarDerivadoTarefaMatrizLista {
    List<DerivadoTarefaMatrizResponse> executar(
            List<DerivadoTarefaMatrizListaResponse> derivadoTarefaMatrizListaResponses
    );
}
