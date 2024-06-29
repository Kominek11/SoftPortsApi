package br.com.softports.core.internal.regra.expression;

import br.com.softports.core.internal.common.entity.QRegra;
import br.com.softports.core.internal.common.enumeration.RegraSituacao;
import com.querydsl.core.types.dsl.BooleanExpression;


import java.util.Objects;

public class RegraExpressions {

    private static final QRegra REGRA = QRegra.regra;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long regraId) {
        return Objects.nonNull(regraId) ? REGRA.id.eq(regraId) : null;
    }

    public static BooleanExpression situacao(RegraSituacao regraSituacao) {
        return Objects.nonNull(regraSituacao)
                ? REGRA.situacao.eq(regraSituacao) : null;
    }
}
