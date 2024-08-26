package br.com.softports.core.internal.tarefa.expression;

import br.com.softports.core.internal.common.entity.QProjeto;
import br.com.softports.core.internal.common.entity.QTarefa;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.ZoneId;
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

    public static BooleanExpression projetoId(Long projetoId) {
        return Objects.nonNull(projetoId) ? TAREFA.projeto.id.eq(projetoId) : null;
    }

    public static BooleanExpression fechada(Boolean fechada) {
        return Objects.nonNull(fechada) ? TAREFA.fechada.eq(fechada) : null;
    }

    public static BooleanExpression entre(LocalDate dataInicio, LocalDate dataFim) {
        Date dataInicioFormatada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataFimFormatada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return TAREFA.dataCriacao.between(dataInicioFormatada, dataFimFormatada);
    }
}
