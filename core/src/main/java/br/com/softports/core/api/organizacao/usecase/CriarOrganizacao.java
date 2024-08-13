package br.com.softports.core.api.organizacao.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;

public interface CriarOrganizacao {

    OrganizacaoResponse executar(String nome);
}
