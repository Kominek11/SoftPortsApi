package br.com.softports.core.api.dashboard.usecase;

import br.com.softports.core.api.dashboard.dto.MetricasResponse;

public interface BuscarMetricas {

    MetricasResponse executar(Long projetoId);
}