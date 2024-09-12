package br.com.softports.core.api.usuario.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AtualizarUsuarioRequest(
        String firstName,
        String email,
        String username
) {}