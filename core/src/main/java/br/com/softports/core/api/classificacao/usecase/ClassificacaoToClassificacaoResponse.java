package br.com.softports.core.api.classificacao.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.internal.common.entity.Classificacao;

public interface ClassificacaoToClassificacaoResponse {

    ClassificacaoResponse executar(Classificacao classificacao);

}
