package br.com.softports.core.api.derivado.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;

public interface BuscarDerivados {

    Pagina<DerivadoResponse> executar(Integer tamanhoPagina,
                                      Integer numeroPagina,
                                      String ordenadoPor,
                                      String direcao,
                                      Long projetoId);
}
