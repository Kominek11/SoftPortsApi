package br.com.softports.core.api.usuario.usecase;

import java.util.UUID;

public interface CriarUsuarioKeycloak {
    UUID executar(String nomeSobrenome, String email, String username);
}
