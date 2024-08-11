package br.com.softports.core.api.organizacao.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.regra.dto.RegraResponse;

public interface BuscarOrganizacoes {

    Pagina<OrganizacaoResponse> executar(Integer tamanhoPagina,
                                         Integer numeroPagina,
                                         String ordenadoPor,
                                         String direcao);
}
