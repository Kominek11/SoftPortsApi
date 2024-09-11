package br.com.softports.core.api.usuario.usecase;

import java.util.UUID;

public interface CriarUsuarioKeycloak {
    UUID executar(String nome, String sobrenome, String email, Boolean emailVerified, String username);
}
