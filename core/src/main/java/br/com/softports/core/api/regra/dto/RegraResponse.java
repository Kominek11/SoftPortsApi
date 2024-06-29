package br.com.softports.core.api.regra.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record RegraResponse(
        Long id,
        String nome,
        String situacao,
        String emailNotificacao,
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        LocalDateTime dataInclusao,
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        LocalDateTime dataInativacao
) {}
