package br.com.softports.core.api.usuario.usecase;

import java.util.UUID;

public interface DeletarUsuarioKeycloak {
    void executar(UUID usuarioId);
}
