package br.com.softports.core.internal.tarefa.expression;

import br.com.softports.core.internal.common.entity.QProjeto;
import br.com.softports.core.internal.common.entity.QTarefa;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class TarefaExpressions {

    private static final QTarefa TAREFA = QTarefa.tarefa;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long tarefaId) {
        return Objects.nonNull(tarefaId) ? TAREFA.id.eq(tarefaId) : null;
    }
}
