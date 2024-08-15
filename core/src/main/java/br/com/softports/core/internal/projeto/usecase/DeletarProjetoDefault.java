package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.DeletarOrganizacao;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletarProjetoDefault implements DeletarProjeto {

    private final ProjetoRepository projetoRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(ProjetoExpressions.id(id));
        Projeto projeto = projetoRepository.buscar(filtro).orElseThrow();
        projetoRepository.apagar(projeto);
    }
}