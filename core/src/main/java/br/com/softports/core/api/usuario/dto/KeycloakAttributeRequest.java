package br.com.softports.core.api.usuario.dto;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;

@Builder
public record KeycloakAttributeRequest(
        String username,
        String firstName,
        String lastName,
        String email,
        Map<String, Object> attributes
) {
}
