package br.com.softports.core.internal.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.usecase.OrganizacaoToOrganizacaoResponse;
import br.com.softports.core.internal.common.entity.Organizacao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrganizacaoToOrganizacaoResponseDefault implements OrganizacaoToOrganizacaoResponse {

    @Override
    public OrganizacaoResponse executar(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }
}
