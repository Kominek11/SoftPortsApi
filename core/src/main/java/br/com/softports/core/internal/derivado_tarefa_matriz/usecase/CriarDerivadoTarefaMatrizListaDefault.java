package br.com.softports.core.internal.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizListaResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.repository.DerivadoTarefaMatrizRepository;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatrizLista;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.DerivadoTarefaMatriz;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.derivado.expression.DerivadoExpressions;
import br.com.softports.core.internal.derivado_tarefa_matriz.expressions.DerivadoTarefaMatrizExpressions;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CriarDerivadoTarefaMatrizListaDefault implements CriarDerivadoTarefaMatrizLista {

    private final DerivadoRepository derivadoRepository;
    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final DerivadoTarefaMatrizRepository derivadoTarefaMatrizRepository;
    private final DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse
                  derivadoTarefaMatrizToDerivadoTarefaMatrizResponse;

    @Override
    public List<DerivadoTarefaMatrizResponse> executar(
            List<DerivadoTarefaMatrizListaResponse> derivadoTarefaMatrizListaResponses
    ) {
        List<DerivadoTarefaMatriz> derivadoTarefaMatrizList = new ArrayList<>();
        derivadoTarefaMatrizListaResponses.forEach(derivadoTarefaMatrizResponse -> {
            BooleanBuilder filtroDerivado = new BooleanBuilder()
                    .and(DerivadoExpressions.id(derivadoTarefaMatrizResponse.derivadoId()));
            BooleanBuilder filtroTarefa = new BooleanBuilder()
                    .and(TarefaExpressions.id(derivadoTarefaMatrizResponse.tarefaId()));
            BooleanBuilder filtroProjeto = new BooleanBuilder()
                    .and(ProjetoExpressions.id(derivadoTarefaMatrizResponse.projetoId()));
            BooleanBuilder filtroExistente = new BooleanBuilder()
                    .and(DerivadoExpressions.id(derivadoTarefaMatrizResponse.derivadoId()))
                    .and(TarefaExpressions.id(derivadoTarefaMatrizResponse.tarefaId()))
                    .and(ProjetoExpressions.id(derivadoTarefaMatrizResponse.projetoId()));
            if (derivadoTarefaMatrizRepository.buscar(filtroExistente).isPresent()) {
                DerivadoTarefaMatriz derivadoTarefaMatriz = derivadoTarefaMatrizRepository.buscar(filtroExistente)
                        .orElseThrow();
                derivadoTarefaMatrizRepository.apagar(derivadoTarefaMatriz);
            }
            Derivado derivado = derivadoRepository.buscar(filtroDerivado).orElseThrow();
            Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
            Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
            DerivadoTarefaMatriz derivadoTarefaMatriz = new DerivadoTarefaMatriz();
            derivadoTarefaMatriz.setDerivado(derivado);
            derivadoTarefaMatriz.setTarefa(tarefa);
            derivadoTarefaMatriz.setValor(derivadoTarefaMatrizResponse.valor());
            derivadoTarefaMatriz.setProjeto(projeto);
            derivadoTarefaMatrizList.add(derivadoTarefaMatriz);
        });
        derivadoTarefaMatrizRepository.salvarTodos(derivadoTarefaMatrizList);
        return derivadoTarefaMatrizList.stream()
                .map(derivadoTarefaMatrizToDerivadoTarefaMatrizResponse::executar)
                .toList();
    }
}