package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.enumerate.UsuarioRole;

public interface CriarUsuario {
    UsuarioResponse executar(String nome, String sobrenome, String email, Boolean emailVerified, String username);
}
