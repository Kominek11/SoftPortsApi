package br.com.softports.core.internal.classificacao.expression;

import br.com.softports.core.internal.common.entity.QClassificacao;
import br.com.softports.core.internal.common.entity.QComentario;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class ClassificacaoExpressions {

    private static final QClassificacao CLASSIFICACAO = QClassificacao.classificacao;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long classificacaoId) {
        return Objects.nonNull(classificacaoId) ? CLASSIFICACAO.id.eq(classificacaoId) : null;
    }
}
