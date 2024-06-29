package br.com.softports.core.api.regra.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.regra.dto.RegraResponse;
import br.com.softports.core.internal.common.enumeration.RegraSituacao;

public interface BuscarRegras {

    Pagina<RegraResponse> executar(Integer tamanhoPagina,
                                   Integer numeroPagina,
                                   String ordenadoPor,
                                   String direcao,
                                   RegraSituacao situacao);

    RegraResponse executar(Long regraId);
}
