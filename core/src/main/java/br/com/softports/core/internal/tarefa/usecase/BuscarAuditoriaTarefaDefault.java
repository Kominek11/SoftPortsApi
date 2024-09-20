package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.tarefa.usecase.BuscarAuditoriaTarefa;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.api.tarefa_aud.repository.TarefaAudRepository;
import br.com.softports.core.api.tarefa_aud.usecase.TarefaAudToTarefaAudResponse;
import br.com.softports.core.internal.tarefa_aud.expression.TarefaAudExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarAuditoriaTarefaDefault implements BuscarAuditoriaTarefa {

    private final TarefaAudRepository tarefaAudRepository;
    private final TarefaAudToTarefaAudResponse tarefaAudToTarefaAudResponse;

    @Override
    public Pagina<TarefaAudResponse> executar(Long id,
                                              Integer tamanhoPagina,
                                              Integer numeroPagina,
                                              String ordenadoPor,
                                              String direcao) {
        BooleanBuilder filtroTarefaAud = new BooleanBuilder().and(TarefaAudExpressions.id(id));
        List<TarefaAudResponse> tarefasAud = tarefaAudRepository
                .buscarTodos(
                        filtroTarefaAud,
                        tamanhoPagina,
                        numeroPagina,
                        ordenadoPor,
                        direcao)
                .stream()
                .map(tarefaAudToTarefaAudResponse::executar)
                .toList();
        return paginar(tamanhoPagina, numeroPagina, tarefasAud, filtroTarefaAud);
    }

    private Pagina<TarefaAudResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                           List<TarefaAudResponse> tarefasAud, BooleanBuilder filtro) {
        Long tarefaAudQuantidade = tarefaAudRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) tarefaAudQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas,
                tamanhoPagina, tarefaAudQuantidade, tarefasAud);
    }

}