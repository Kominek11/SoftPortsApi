package br.com.softports.core.internal.organizacao.expression;

import br.com.softports.core.internal.common.entity.QOrganizacao;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class OrganizacaoExpressions {

    private static final QOrganizacao ORGANIZACAO = QOrganizacao.organizacao;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long organizacaoId) {
        return Objects.nonNull(organizacaoId) ? ORGANIZACAO.id.eq(organizacaoId) : null;
    }
}
