package br.com.softports.core.internal.solicitacao.expression;

import br.com.softports.core.internal.common.entity.QSolicitacao;
import br.com.softports.core.internal.common.entity.Usuario;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SolicitacaoExpressions {

    private static final QSolicitacao SOLICITACAO = QSolicitacao.solicitacao;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long solicitacaoId) {
        return Objects.nonNull(solicitacaoId) ? SOLICITACAO.id.eq(solicitacaoId) : null;
    }

    public static BooleanExpression projetoId(Long projetoId) {
        return Objects.nonNull(projetoId) ? SOLICITACAO.projeto.id.eq(projetoId) : null;
    }

    public static BooleanExpression fechada(Boolean fechada) {
        return Objects.nonNull(fechada) ? SOLICITACAO.fechada.eq(fechada) : null;
    }

    public static BooleanExpression titulo(String titulo) {
        return Objects.nonNull(titulo) ? SOLICITACAO.titulo.likeIgnoreCase("%" + titulo + "%") : null;
    }

    public static BooleanExpression usuarios(Set<Usuario> usuarios) {
        return Objects.nonNull(usuarios) && !usuarios.isEmpty() ?
                usuarios.stream()
                        .map(SOLICITACAO.usuarios::contains)
                        .reduce(BooleanExpression::or)
                        .orElse(null)
                : null;
    }

    public static BooleanExpression prioridade(List<Long> prioridades) {
        return Objects.nonNull(prioridades)
                ? SOLICITACAO.prioridade.in(prioridades)
                : null;
    }

    public static BooleanExpression classificacao(List<Long> classificacoes) {
        return Objects.nonNull(classificacoes)
                ? SOLICITACAO.classificacao.id.in(classificacoes)
                : null;
    }

    public static BooleanExpression subClassificacao(List<Long> subClassificacoes) {
        return Objects.nonNull(subClassificacoes)
                ? SOLICITACAO.classificacao.subClassificacao.id.in(subClassificacoes)
                : null;
    }

    public static BooleanExpression entre(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            return null;
        }
        Date dataInicioFormatada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataFimFormatada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return SOLICITACAO.dataFechamento.between(dataInicioFormatada, dataFimFormatada);
    }

    public static BooleanExpression anoCorrente() {
        LocalDate primeiroDiaAno = LocalDate.now().withDayOfYear(1);
        LocalDate ultimoDiaAno = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());

        Date dataInicioFormatada = Date.from(primeiroDiaAno.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataFimFormatada = Date.from(ultimoDiaAno.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return SOLICITACAO.dataFechamento.between(dataInicioFormatada, dataFimFormatada);
    }

}
