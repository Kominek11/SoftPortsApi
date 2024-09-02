package br.com.softports.core.api.usuario.dto;

import br.com.softports.core.api.usuario.enumerate.UsuarioRole;
import lombok.Builder;

@Builder
public record CriarUsuarioRequest(
        String nome,
        String email,
        String username
) {}