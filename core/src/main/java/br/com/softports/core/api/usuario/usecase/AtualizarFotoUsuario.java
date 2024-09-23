package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.UsuarioResponse;

import java.util.UUID;

public interface AtualizarFotoUsuario {
    UsuarioResponse executar(UUID id, byte[] foto);
}
