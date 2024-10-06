package br.com.softports.core.internal.derivado_tarefa_matriz.expressions;

import br.com.softports.core.internal.common.entity.QDerivado;
import br.com.softports.core.internal.common.entity.QDerivadoTarefaMatriz;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class DerivadoTarefaMatrizExpressions {

    private static final QDerivadoTarefaMatriz DERIVADO_TAREFA_MATRIZ = QDerivadoTarefaMatriz.derivadoTarefaMatriz;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long derivadoTarefaMatrizId) {
        return Objects.nonNull(derivadoTarefaMatrizId) ?
                DERIVADO_TAREFA_MATRIZ.id.eq(derivadoTarefaMatrizId) : null;
    }

    public static BooleanExpression projetoId(Long projetoId) {
        return Objects.nonNull(projetoId) ? DERIVADO_TAREFA_MATRIZ.projeto.id.eq(projetoId) : null;
    }

    public static BooleanExpression valor(Boolean valor) {
        return Objects.nonNull(valor) ? DERIVADO_TAREFA_MATRIZ.valor.eq(valor) : null;
    }
}
