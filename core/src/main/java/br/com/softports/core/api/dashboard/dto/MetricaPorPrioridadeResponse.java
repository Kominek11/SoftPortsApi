package br.com.softports.core.api.dashboard.dto;

import lombok.Builder;

@Builder
public record MetricaPorPrioridadeResponse(
        Long prioridadeId,
        double valor
) {}
