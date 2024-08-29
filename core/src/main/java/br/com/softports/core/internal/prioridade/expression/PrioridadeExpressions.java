package br.com.softports.core.internal.prioridade.expression;

import br.com.softports.core.internal.common.entity.QOrganizacao;
import br.com.softports.core.internal.common.entity.QPrioridade;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class PrioridadeExpressions {

    private static final QPrioridade PRIORIDADE = QPrioridade.prioridade;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long prioridadeId) {
        return Objects.nonNull(prioridadeId) ? PRIORIDADE.id.eq(prioridadeId) : null;
    }
}
