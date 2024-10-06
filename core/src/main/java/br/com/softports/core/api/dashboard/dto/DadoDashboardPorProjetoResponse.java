package br.com.softports.core.api.dashboard.dto;

import lombok.Builder;

@Builder
public record DadoDashboardPorProjetoResponse(
        Long valor,
        String nomeProjeto
) {}
