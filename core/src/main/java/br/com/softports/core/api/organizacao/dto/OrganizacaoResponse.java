package br.com.softports.core.api.organizacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrganizacaoResponse(
        Long id,
        String nome
) {}
