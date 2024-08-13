package br.com.softports.core.internal.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.internal.common.entity.Organizacao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarOrganizacaoDefault implements CriarOrganizacao {

    private final OrganizacaoRepository organizacaoRepository;

    @Override
    public OrganizacaoResponse executar(String nome) {
            Organizacao organizacao = new Organizacao();
            organizacao.setNome(nome);
            organizacaoRepository.salvar(organizacao);
        return gerarOrganizacaoResponse(organizacao);
    }

    private OrganizacaoResponse gerarOrganizacaoResponse(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }
}