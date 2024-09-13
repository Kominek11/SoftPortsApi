package br.com.softports.core.internal.tarefa_aud.expression;

import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.common.entity.audited.QTarefaAud;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TarefaAudExpressions {

    private static final QTarefaAud TAREFA_AUD = QTarefaAud.tarefaAud;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long tarefaAudId) {
        return Objects.nonNull(tarefaAudId) ? TAREFA_AUD.id.eq(tarefaAudId) : null;
    }
}
