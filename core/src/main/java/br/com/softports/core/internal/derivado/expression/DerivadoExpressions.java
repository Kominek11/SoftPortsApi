package br.com.softports.core.internal.derivado.expression;

import br.com.softports.core.internal.common.entity.QDerivado;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class DerivadoExpressions {

    private static final QDerivado DERIVADO = QDerivado.derivado;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long derivadoId) {
        return Objects.nonNull(derivadoId) ? DERIVADO.id.eq(derivadoId) : null;
    }

    public static BooleanExpression projetoId(Long projetoId) {
        return Objects.nonNull(projetoId) ? DERIVADO.projeto.id.eq(projetoId) : null;
    }
}
