package br.com.softports.core.internal.organizacao.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.api.organizacao.usecase.DeletarOrganizacao;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletarOrganizacaoDefault implements DeletarOrganizacao {

    private final OrganizacaoRepository organizacaoRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(OrganizacaoExpressions.id(id));
        Organizacao organizacao = organizacaoRepository.buscar(filtro).orElseThrow();
        organizacaoRepository.apagar(organizacao);
    }
}