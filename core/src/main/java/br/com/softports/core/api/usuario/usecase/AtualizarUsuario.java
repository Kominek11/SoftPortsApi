package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;

import java.util.List;
import java.util.UUID;

public interface AtualizarUsuario {
    UsuarioResponse executar(UUID id, String nome, String email, String username);
}
