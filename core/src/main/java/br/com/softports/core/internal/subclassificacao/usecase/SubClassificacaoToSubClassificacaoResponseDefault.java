package br.com.softports.core.internal.subclassificacao.usecase;

import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.subclassificacao.usecase.SubClassificacaoToSubClassificacaoResponse;
import br.com.softports.core.internal.common.entity.SubClassificacao;

public class SubClassificacaoToSubClassificacaoResponseDefault implements SubClassificacaoToSubClassificacaoResponse {

    @Override
    public SubClassificacaoResponse executar(SubClassificacao subClassificacao) {
        return new SubClassificacaoResponse(
                subClassificacao.getId(),
                subClassificacao.getNome()
        );
    }
}
