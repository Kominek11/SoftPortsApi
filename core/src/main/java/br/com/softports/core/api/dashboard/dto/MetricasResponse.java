package br.com.softports.core.api.dashboard.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MetricasResponse(
        double overallConflictDensity,
        List<MetricaPorPrioridadeResponse> priorityConflictDensity
) {}
