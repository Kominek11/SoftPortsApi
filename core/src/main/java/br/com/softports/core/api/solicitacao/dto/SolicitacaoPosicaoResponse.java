package br.com.softports.core.api.solicitacao.dto;

import lombok.Builder;

@Builder
public record SolicitacaoPosicaoResponse(
        Long id,
        Long status,
        Long posicao
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long status() {
        return status;
    }

    @Override
    public Long posicao() {
        return posicao;
    }
}
