package br.com.softports.core.api.dashboard.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record DadoDashboardPorAnoResponse(
        Integer year,
        Integer month,
        Integer valorIncidente,
        Integer valorProblema,
        Integer valorMudanca
) {}
