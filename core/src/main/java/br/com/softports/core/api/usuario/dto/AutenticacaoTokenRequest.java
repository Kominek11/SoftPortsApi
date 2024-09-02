package br.com.softports.core.api.usuario.dto;

import lombok.Builder;

@Builder
public record AutenticacaoTokenRequest(
        String grantType,
        String clientId,
        String clientSecret

) {
}
