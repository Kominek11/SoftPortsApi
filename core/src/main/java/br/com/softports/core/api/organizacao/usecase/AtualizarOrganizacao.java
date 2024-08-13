package br.com.softports.core.api.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;

public interface AtualizarOrganizacao {

    OrganizacaoResponse executar(Long id, String nome);
}
