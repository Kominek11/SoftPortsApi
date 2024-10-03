package br.com.softports.core.api.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.DerivadoTarefaMatriz;

public interface DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse {

    DerivadoTarefaMatrizResponse executar(DerivadoTarefaMatriz derivadoTarefaMatriz);
}
