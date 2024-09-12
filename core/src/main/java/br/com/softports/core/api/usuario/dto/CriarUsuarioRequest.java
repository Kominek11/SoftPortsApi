package br.com.softports.core.api.usuario.dto;

import br.com.softports.core.api.usuario.enumerate.UsuarioRole;
import lombok.Builder;

import java.util.List;

@Builder
public record CriarUsuarioRequest(
        String nome,
        String sobrenome,
        String email,
        Boolean emailVerified,
        String username,
        List<KeycloakRoleResponse> realmRoles
) {}