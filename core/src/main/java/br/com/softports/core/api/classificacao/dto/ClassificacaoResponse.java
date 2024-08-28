package br.com.softports.core.api.classificacao.dto;

import lombok.Builder;

@Builder
public record ClassificacaoResponse(
        Long id,
        String nome
) {}
