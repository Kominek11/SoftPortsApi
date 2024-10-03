package br.com.softports.core.internal.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.repository.DerivadoTarefaMatrizRepository;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.BuscarDerivadosTarefaMatriz;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse;
import br.com.softports.core.internal.derivado_tarefa_matriz.expressions.DerivadoTarefaMatrizExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarDerivadosTarefaMatrizDefault implements BuscarDerivadosTarefaMatriz {

    private final DerivadoTarefaMatrizRepository derivadoTarefaMatrizRepository;
    private final DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse
                  derivadoTarefaMatrizToDerivadoTarefaMatrizResponse;

    @Override
    public Pagina<DerivadoTarefaMatrizResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                                         String ordenadoPor, String direcao,
                                                         Long projetoId) {
        BooleanBuilder filtro = new BooleanBuilder()
                .and(DerivadoTarefaMatrizExpressions.projetoId(projetoId));
            List<DerivadoTarefaMatrizResponse> derivadosTarefaMatriz = derivadoTarefaMatrizRepository
                    .buscarTodos(filtro,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(derivadoTarefaMatrizToDerivadoTarefaMatrizResponse::executar)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, derivadosTarefaMatriz, filtro);
    }

    private Pagina<DerivadoTarefaMatrizResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<DerivadoTarefaMatrizResponse> derivados, BooleanBuilder filtro) {
        Long derivadoTarefaMatrizQuantidade = derivadoTarefaMatrizRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) derivadoTarefaMatrizQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas,
                tamanhoPagina, derivadoTarefaMatrizQuantidade, derivados);
    }
}