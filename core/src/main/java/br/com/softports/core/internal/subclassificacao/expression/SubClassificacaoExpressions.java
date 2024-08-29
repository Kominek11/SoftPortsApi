package br.com.softports.core.internal.subclassificacao.expression;

import br.com.softports.core.internal.common.entity.QClassificacao;
import br.com.softports.core.internal.common.entity.QSubClassificacao;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class SubClassificacaoExpressions {

    private static final QSubClassificacao SUB_CLASSIFICACAO = QSubClassificacao.subClassificacao;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long subClassificacaoId) {
        return Objects.nonNull(subClassificacaoId) ? SUB_CLASSIFICACAO.id.eq(subClassificacaoId) : null;
    }
}
