package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.AtualizarUsuarioKeycloakResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;

import java.util.UUID;

public interface AtualizarUsuarioKeycloak {
    AtualizarUsuarioKeycloakResponse executar(UUID id, String nome, String email, String username);
}
