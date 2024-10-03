package br.com.softports.core.api.derivado.dto;

import lombok.Builder;

@Builder
public record DerivadoListaResponse(
        Long id,
        String nome,
        Long projetoId
) {}
