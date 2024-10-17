package br.com.softports.core.internal.dashboard.usecase;

import br.com.softports.core.api.dashboard.dto.DashboardResponse;
import br.com.softports.core.api.dashboard.dto.MetricasResponse;
import br.com.softports.core.api.dashboard.usecase.BuscarDashboard;
import br.com.softports.core.api.dashboard.usecase.BuscarMetricas;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarMetricasDefault implements BuscarMetricas {

    private final TarefaRepository tarefaRepository;

    @Override
    public MetricasResponse executar(Long projetoId) {
        return new MetricasResponse(
                tarefaRepository.calcularDensidadeDeConflito(projetoId),
                tarefaRepository.calcularDensidadeDeConflitoPorPrioridade(projetoId)
        );
    }
}