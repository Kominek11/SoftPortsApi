package br.com.softports.core.api.solicitacao.repository;

import br.com.softports.core.api.common.repository.Repositorio;
import br.com.softports.core.api.dashboard.dto.DadoDashboardPorAnoResponse;
import br.com.softports.core.api.dashboard.dto.DadoDashboardPorProjetoResponse;
import br.com.softports.core.api.dashboard.dto.DadoDashboardResponse;
import br.com.softports.core.api.dashboard.dto.MetricaPorPrioridadeResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.common.entity.Tarefa;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoRepository extends Repositorio<Solicitacao> {

    Optional<Long> findMaxPosicao(Long status);
}
