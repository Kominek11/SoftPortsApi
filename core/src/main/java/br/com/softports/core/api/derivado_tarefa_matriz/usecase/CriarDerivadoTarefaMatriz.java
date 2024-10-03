package br.com.softports.core.api.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;

public interface CriarDerivadoTarefaMatriz {
    DerivadoTarefaMatrizResponse executar(Long derivadoId,
                                          Long tarefaId,
                                          Boolean valor,
                                          Long projetoId);
}
