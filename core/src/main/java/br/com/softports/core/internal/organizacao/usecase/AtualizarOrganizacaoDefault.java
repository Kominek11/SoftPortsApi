package br.com.softports.core.internal.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.AtualizarOrganizacao;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarOrganizacaoDefault implements AtualizarOrganizacao {

    private final OrganizacaoRepository organizacaoRepository;

    @Override
    public OrganizacaoResponse executar(Long id, String nome) {
        BooleanBuilder filtro = new BooleanBuilder().and(UsuarioExpressions.id(id));
            Organizacao organizacao = organizacaoRepository.buscar(filtro).orElseThrow();
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