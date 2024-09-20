package br.com.softports.core.internal.classificacao.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.usecase.OrganizacaoToOrganizacaoResponse;
import br.com.softports.core.api.subclassificacao.usecase.SubClassificacaoToSubClassificacaoResponse;
import br.com.softports.core.internal.common.entity.Classificacao;
import br.com.softports.core.internal.common.entity.Organizacao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClassificacaoToClassificacaoResponseDefault implements ClassificacaoToClassificacaoResponse {

    private final SubClassificacaoToSubClassificacaoResponse subClassificacaoToSubClassificacaoResponse;

    @Override
    public ClassificacaoResponse executar(Classificacao classificacao) {
        return new ClassificacaoResponse(
                classificacao.getId(),
                subClassificacaoToSubClassificacaoResponse
                        .executar(classificacao.getSubClassificacao()).id()
        );
    }
}
