package br.com.softports.core.api.projeto.dto;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record ProjetoResponse(
        Long id,
        String nome,
        OrganizacaoResponse organizacao
) {}
