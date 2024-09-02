package br.com.softports.core.internal.tarefa.expression;

import br.com.softports.core.internal.common.entity.QProjeto;
import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.Usuario;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public static BooleanExpression titulo(String titulo) {
        return Objects.nonNull(titulo) ? TAREFA.titulo.likeIgnoreCase("%" + titulo + "%") : null;
    }

    public static BooleanExpression usuarios(Set<Usuario> usuarios) {
        return Objects.nonNull(usuarios) && !usuarios.isEmpty() ?
                usuarios.stream()
                        .map(TAREFA.usuarios::contains)
                        .reduce(BooleanExpression::or)
                        .orElse(null)
                : null;
    }

    public static BooleanExpression prioridade(List<Long> prioridades) {
        return Objects.nonNull(prioridades)
                ? TAREFA.prioridade.in(prioridades)
                : null;
    }

    public static BooleanExpression classificacao(List<Long> classificacoes) {
        return Objects.nonNull(classificacoes)
                ? TAREFA.classificacao.id.in(classificacoes)
                : null;
    }

    public static BooleanExpression subClassificacao(List<Long> subClassificacoes) {
        return Objects.nonNull(subClassificacoes)
                ? TAREFA.classificacao.subClassificacao.id.in(subClassificacoes)
                : null;
    }

    public static BooleanExpression entre(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            return null;
        }
        Date dataInicioFormatada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataFimFormatada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return TAREFA.dataFechamento.between(dataInicioFormatada, dataFimFormatada);
    }
}
