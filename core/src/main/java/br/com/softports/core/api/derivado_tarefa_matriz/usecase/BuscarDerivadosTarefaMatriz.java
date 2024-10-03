package br.com.softports.core.api.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;

public interface BuscarDerivadosTarefaMatriz {

    Pagina<DerivadoTarefaMatrizResponse> executar(Integer tamanhoPagina,
                                                  Integer numeroPagina,
                                                  String ordenadoPor,
                                                  String direcao,
                                                  Long projetoId);
}
