package br.com.softports.core.internal.dashboard.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.dashboard.dto.DadoDashboardResponse;
import br.com.softports.core.api.dashboard.dto.DashboardResponse;
import br.com.softports.core.api.dashboard.usecase.BuscarDashboard;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.BuscarDerivados;
import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.derivado.expression.DerivadoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuscarDashboardDefault implements BuscarDashboard {

    private final TarefaRepository tarefaRepository;

    @Override
    public DashboardResponse executar() {
        tarefaRepository.contarTarefasPorAno();
        return new DashboardResponse(tarefaRepository.contarTarefas(), tarefaRepository.contarTarefasPorProjeto());
    }
}