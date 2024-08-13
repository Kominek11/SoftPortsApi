package br.com.softports.core.api.projeto.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;

public interface BuscarProjetos {

    Pagina<ProjetoResponse> executar(Integer tamanhoPagina,
                                     Integer numeroPagina,
                                     String ordenadoPor,
                                     String direcao);

    ProjetoResponse executar(Long id);
}
