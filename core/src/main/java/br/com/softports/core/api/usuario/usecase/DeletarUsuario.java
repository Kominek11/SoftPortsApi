package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.UsuarioResponse;

import java.util.UUID;

public interface DeletarUsuario {
    void executar(UUID usuarioId);
}
