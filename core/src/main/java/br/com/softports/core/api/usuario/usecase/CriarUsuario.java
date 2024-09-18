package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.enumerate.UsuarioRole;

import java.util.List;
import java.util.Map;

public interface CriarUsuario {
    UsuarioResponse executar(String nome, String sobrenome, String email,
                             Boolean emailVerified, String username, List<KeycloakRoleResponse> realmRoles,
                             Map<String, Object> attributes);
}
