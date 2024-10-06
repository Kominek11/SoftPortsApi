package br.com.softports.core.api.tarefa.repository;

import br.com.softports.core.api.common.repository.Repositorio;
import br.com.softports.core.api.dashboard.dto.DadoDashboardPorAnoResponse;
import br.com.softports.core.api.dashboard.dto.DadoDashboardPorProjetoResponse;
import br.com.softports.core.api.dashboard.dto.DadoDashboardResponse;
import br.com.softports.core.internal.common.entity.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends Repositorio<Tarefa> {

    Optional<Long> findMaxPosicao(Long status);

    List<Tarefa> findAllByIds(List<Long> ids);

    DadoDashboardResponse contarTarefas();

    List<DadoDashboardPorProjetoResponse> contarTarefasPorProjeto();

    List<DadoDashboardPorAnoResponse> contarTarefasPorAno();
}
