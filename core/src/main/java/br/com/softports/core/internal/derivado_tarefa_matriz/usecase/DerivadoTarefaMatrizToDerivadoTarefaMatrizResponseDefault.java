package br.com.softports.core.internal.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.internal.common.entity.DerivadoTarefaMatriz;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DerivadoTarefaMatrizToDerivadoTarefaMatrizResponseDefault implements DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse {

    private final DerivadoToDerivadoResponse derivadoToDerivadoResponse;
    private final TarefaToTarefaResponse tarefaToTarefaResponse;
    private final ProjetoToProjetoResponse projetoToProjetoResponse;

    @Override
    public DerivadoTarefaMatrizResponse executar(DerivadoTarefaMatriz derivadoTarefaMatriz) {
        return new DerivadoTarefaMatrizResponse(
                derivadoTarefaMatriz.getId(),
                derivadoToDerivadoResponse.executar(derivadoTarefaMatriz.getDerivado()),
                tarefaToTarefaResponse.executar(derivadoTarefaMatriz.getTarefa()),
                derivadoTarefaMatriz.getValor(),
                projetoToProjetoResponse.executar(derivadoTarefaMatriz.getProjeto())
        );
    }
}
