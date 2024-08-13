package br.com.softports.core.internal.projeto.expression;

import br.com.softports.core.internal.common.entity.QOrganizacao;
import br.com.softports.core.internal.common.entity.QProjeto;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class ProjetoExpressions {

    private static final QProjeto PROJETO = QProjeto.projeto;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long projetoId) {
        return Objects.nonNull(projetoId) ? PROJETO.id.eq(projetoId) : null;
    }
}
