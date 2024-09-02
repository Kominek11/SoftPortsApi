package br.com.softports.core.api.comentario.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record ComentarioResponse(
        Long id,
        String conteudo,
        LocalDateTime dataCriacao,
        String nome
) {}
