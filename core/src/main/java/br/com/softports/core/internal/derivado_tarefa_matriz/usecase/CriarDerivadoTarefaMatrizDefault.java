package br.com.softports.core.internal.derivado_tarefa_matriz.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.CriarDerivado;
import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.repository.DerivadoTarefaMatrizRepository;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatriz;
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

@RequiredArgsConstructor
public class CriarDerivadoTarefaMatrizDefault implements CriarDerivadoTarefaMatriz {

    private final DerivadoRepository derivadoRepository;
    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final DerivadoTarefaMatrizRepository derivadoTarefaMatrizRepository;
    private final DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse
                  derivadoTarefaMatrizToDerivadoTarefaMatrizResponse;

    @Override
    public DerivadoTarefaMatrizResponse executar(Long derivadoId,
                                                 Long tarefaId,
                                                 Boolean valor,
                                                 Long projetoId) {
        BooleanBuilder filtroDerivado = new BooleanBuilder()
                .and(DerivadoExpressions.id(derivadoId));
        BooleanBuilder filtroTarefa = new BooleanBuilder()
                .and(TarefaExpressions.id(tarefaId));
        BooleanBuilder filtroProjeto = new BooleanBuilder()
                .and(ProjetoExpressions.id(projetoId));
        BooleanBuilder filtroExistente = new BooleanBuilder()
                .and(DerivadoExpressions.id(derivadoId))
                .and(TarefaExpressions.id(tarefaId))
                .and(ProjetoExpressions.id(projetoId));
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
        derivadoTarefaMatriz.setValor(valor);
        derivadoTarefaMatriz.setProjeto(projeto);
        derivadoTarefaMatrizRepository.salvar(derivadoTarefaMatriz);
        return derivadoTarefaMatrizToDerivadoTarefaMatrizResponse.executar(derivadoTarefaMatriz);
    }
}