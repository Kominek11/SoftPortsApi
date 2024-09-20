package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.organizacao.usecase.OrganizacaoToOrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.internal.common.entity.Projeto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoToProjetoResponseDefault implements ProjetoToProjetoResponse {

    private final OrganizacaoToOrganizacaoResponse organizacaoToOrganizacaoResponse;

    @Override
     public ProjetoResponse executar(Projeto projeto) {
        return new ProjetoResponse(
                projeto.getId(),
                projeto.getNome(),
                organizacaoToOrganizacaoResponse.executar(projeto.getOrganizacao())
        );
    }
}
