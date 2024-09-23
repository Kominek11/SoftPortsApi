package br.com.softports.core.api.usuario.dto;

import lombok.Builder;

import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Builder
public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        UUID keycloakId,
        List<String> roles,
        byte[] foto
) {}
