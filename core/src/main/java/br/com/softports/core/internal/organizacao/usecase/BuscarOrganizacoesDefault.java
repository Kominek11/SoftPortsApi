package br.com.softports.core.internal.organizacao.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.BuscarOrganizacoes;
import br.com.softports.core.internal.common.entity.Organizacao;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarOrganizacoesDefault implements BuscarOrganizacoes {

    private final OrganizacaoRepository organizacaoRepository;

    @Override
    public Pagina<OrganizacaoResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                                String ordenadoPor, String direcao) {
            List<OrganizacaoResponse> regras = organizacaoRepository
                    .buscarTodos(null,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(this::gerarOrganizacaoResponse)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, regras, null);
    }

    private Pagina<OrganizacaoResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<OrganizacaoResponse> organizacoes, BooleanBuilder filtro) {
        Long regraQuantidade = organizacaoRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) regraQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, regraQuantidade, organizacoes);
    }

    private OrganizacaoResponse gerarOrganizacaoResponse(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }
}