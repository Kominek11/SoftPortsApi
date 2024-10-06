package br.com.softports.core.api.dashboard.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record DadoDashboardPorAnoResponse(
        Long valorIncidente,
        Long valorProblema,
        Long valorMudanca,
        Date data
) {}
