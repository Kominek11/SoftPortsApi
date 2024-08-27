package br.com.softports.core.internal.comentario.expression;

import br.com.softports.core.internal.common.entity.QComentario;
import br.com.softports.core.internal.common.entity.QTarefa;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class ComentarioExpressions {

    private static final QComentario COMENTARIO = QComentario.comentario;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long comentarioId) {
        return Objects.nonNull(comentarioId) ? COMENTARIO.id.eq(comentarioId) : null;
    }

    public static BooleanExpression tarefaId(Long tarefaId) {
        return Objects.nonNull(tarefaId) ? COMENTARIO.tarefa.id.eq(tarefaId) : null;
    }
}
