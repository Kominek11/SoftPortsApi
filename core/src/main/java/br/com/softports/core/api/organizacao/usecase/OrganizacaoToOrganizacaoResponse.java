package br.com.softports.core.api.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.internal.common.entity.Organizacao;

public interface OrganizacaoToOrganizacaoResponse {

    OrganizacaoResponse executar(Organizacao organizacao);

}
