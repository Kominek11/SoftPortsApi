package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;

import java.util.List;
import java.util.UUID;

public interface CriarUsuarioKeycloak {
    UUID executar(String nome, String sobrenome, String email,
                  Boolean emailVerified, String username, List<KeycloakRoleResponse> realmRoles);
}
