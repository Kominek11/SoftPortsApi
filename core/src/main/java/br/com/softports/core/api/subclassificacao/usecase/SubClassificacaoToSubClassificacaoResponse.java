package br.com.softports.core.api.subclassificacao.usecase;

import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.internal.common.entity.SubClassificacao;

public interface SubClassificacaoToSubClassificacaoResponse {

    SubClassificacaoResponse executar(SubClassificacao subClassificacao);

}
