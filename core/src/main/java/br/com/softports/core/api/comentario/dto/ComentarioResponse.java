package br.com.softports.core.api.comentario.dto;

import lombok.Builder;

@Builder
public record ComentarioResponse(
        Long id,
        String conteudo
) {}
