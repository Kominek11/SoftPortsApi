package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.BuscarOrganizacoes;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarProjetosDefault implements BuscarProjetos {

    private final ProjetoRepository projetoRepository;
    private final ProjetoToProjetoResponse projetoToProjetoResponse;

    @Override
    public Pagina<ProjetoResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                            String ordenadoPor, String direcao) {
            List<ProjetoResponse> projetos = projetoRepository
                    .buscarTodos(null,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(projetoToProjetoResponse::executar)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, projetos, null);
    }

    @Override
    public ProjetoResponse executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(ProjetoExpressions.id(id));
        Projeto projeto = projetoRepository.buscar(filtro).orElseThrow();
        return projetoToProjetoResponse.executar(projeto);
    }

    private Pagina<ProjetoResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<ProjetoResponse> projetos, BooleanBuilder filtro) {
        Long projetoQuantidade = projetoRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) projetoQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, projetoQuantidade, projetos);
    }
}