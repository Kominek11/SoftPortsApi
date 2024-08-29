package br.com.softports.core.api.classificacao.dto;

import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import lombok.Builder;

@Builder
public record ClassificacaoResponse(
        Long id,
        Long subclassificacaoId
) {}
