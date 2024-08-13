package br.com.softports.core.api.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;

public interface DeletarOrganizacao {

    void executar(Long id);
}
