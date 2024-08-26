package br.com.softports.core.internal.tarefa.expression;

import br.com.softports.core.internal.common.entity.QProjeto;
import br.com.softports.core.internal.common.entity.QTarefa;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Date;
import java.util.Objects;

public class TarefaExpressions {

    private static final QTarefa TAREFA = QTarefa.tarefa;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long tarefaId) {
        return Objects.nonNull(tarefaId) ? TAREFA.id.eq(tarefaId) : null;
    }

    public static BooleanExpression fechada(Boolean fechada) {
        return Objects.nonNull(fechada) ? TAREFA.fechada.eq(fechada) : null;
    }

    public static BooleanExpression entre(Date dataInicio, Date dataFim) {
        if (Objects.isNull(dataInicio) && Objects.isNull(dataFim)) {
            return null;
        }
        if (Objects.isNull(dataInicio)) {
            return TAREFA.dataCriacao.loe(dataFim);
        }
        if (Objects.isNull(dataFim)) {
            return TAREFA.dataCriacao.goe(dataInicio);
        }
        return TAREFA.dataCriacao.between(dataInicio, dataFim);
    }
}
