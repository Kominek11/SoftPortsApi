package br.com.softports.core.api.regra.dto;

import lombok.Builder;

@Builder
public record AtualizarSituacaoRegraResponse(
        Long id,
        String situacao
) {}
