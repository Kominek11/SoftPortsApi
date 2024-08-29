package br.com.softports.core.api.classificacao.dto;

import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import lombok.Builder;

@Builder
public record ClassificacaoResponse(
        Long id,
        String nome,
        SubClassificacaoResponse subclassificacao
) {}
