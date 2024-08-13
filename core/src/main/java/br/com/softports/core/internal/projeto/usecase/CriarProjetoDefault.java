package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarProjetoDefault implements CriarProjeto {

    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;

    @Override
    public ProjetoResponse executar(String nome, Long organizacaoId) {
        BooleanBuilder filtro = new BooleanBuilder().and(OrganizacaoExpressions.id(organizacaoId));
        Organizacao organizacao = organizacaoRepository.buscar(filtro).orElseThrow();
        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setOrganizacao(organizacao);
        projetoRepository.salvar(projeto);
        return gerarProjetoResponse(projeto);
    }

    private ProjetoResponse gerarProjetoResponse(Projeto projeto) {
        return new ProjetoResponse(
                projeto.getId(),
                projeto.getNome(),
                gerarOrganizacaoResponse(projeto.getOrganizacao())
        );
    }

    private OrganizacaoResponse gerarOrganizacaoResponse(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }
}