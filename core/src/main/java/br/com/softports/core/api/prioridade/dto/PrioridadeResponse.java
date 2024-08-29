package br.com.softports.core.api.prioridade.dto;

import lombok.Builder;

@Builder
public record PrioridadeResponse(
        Long id,
        String nome
) {}
