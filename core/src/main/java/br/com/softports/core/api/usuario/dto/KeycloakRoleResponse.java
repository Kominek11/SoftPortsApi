package br.com.softports.core.api.usuario.dto;

import java.util.UUID;

public record KeycloakRoleResponse(
        UUID id,
        String name
) {
}
