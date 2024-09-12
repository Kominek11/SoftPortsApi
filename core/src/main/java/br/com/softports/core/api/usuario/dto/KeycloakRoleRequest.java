package br.com.softports.core.api.usuario.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record KeycloakRoleRequest(
        UUID id,
        String name
) {
}
