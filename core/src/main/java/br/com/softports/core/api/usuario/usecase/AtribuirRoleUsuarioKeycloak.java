package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;

import java.util.List;
import java.util.UUID;

public interface AtribuirRoleUsuarioKeycloak {
    void executar(UUID usuarioId, List<KeycloakRoleResponse> roles);
}
