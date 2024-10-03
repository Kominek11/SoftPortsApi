package br.com.softports.core.api.derivado.dto;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import lombok.Builder;

@Builder
public record DerivadoResponse(
        Long id,
        String nome,
        ProjetoResponse projetoResponse
) {}
