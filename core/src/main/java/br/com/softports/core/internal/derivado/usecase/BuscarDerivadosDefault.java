package br.com.softports.core.internal.derivado.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.BuscarDerivados;
import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.internal.derivado.expression.DerivadoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarDerivadosDefault implements BuscarDerivados {

    private final DerivadoRepository derivadoRepository;
    private final DerivadoToDerivadoResponse derivadoToDerivadoResponse;

    @Override
    public Pagina<DerivadoResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                             String ordenadoPor, String direcao,
                                             Long projetoId) {
        BooleanBuilder filtro = new BooleanBuilder()
                .and(DerivadoExpressions.projetoId(projetoId));
            List<DerivadoResponse> derivados = derivadoRepository
                    .buscarTodos(filtro,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(derivadoToDerivadoResponse::executar)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, derivados, filtro);
    }

    private Pagina<DerivadoResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<DerivadoResponse> derivados, BooleanBuilder filtro) {
        Long derivadoQuantidade = derivadoRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) derivadoQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, derivadoQuantidade, derivados);
    }
}