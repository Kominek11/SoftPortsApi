package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.dashboard.dto.DadoDashboardPorAnoResponse;
import br.com.softports.core.api.dashboard.dto.DadoDashboardPorProjetoResponse;
import br.com.softports.core.api.dashboard.dto.DadoDashboardResponse;
import br.com.softports.core.api.dashboard.dto.DashboardResponse;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TarefaRepositoryDefault extends RepositorioDefault<Tarefa, Long> implements TarefaRepository {

    public TarefaRepositoryDefault(EntityManager entityManager) {
        super(Tarefa.class, entityManager, QTarefa.tarefa);
    }

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Optional<Long> findMaxPosicao(Long status) {
        QTarefa tarefa = QTarefa.tarefa;
        Long maxPosicao = queryFactory
                .select(tarefa.posicao.max())
                .from(tarefa)
                .where(tarefa.status.eq(status))
                .fetchOne();
        return Optional.ofNullable(maxPosicao);
    }

    @Override
    public List<Tarefa> findAllByIds(List<Long> ids) {
        QTarefa tarefa = QTarefa.tarefa;

        return queryFactory
                .selectFrom(tarefa)
                .where(tarefa.id.in(ids))
                .fetch();
    }

    @Override
    public DadoDashboardResponse contarTarefas() {
        QTarefa tarefa = QTarefa.tarefa;

        return queryFactory
                .select(Projections.constructor(DadoDashboardResponse.class,
                        JPAExpressions.select(tarefa.id.count())
                                .from(tarefa)
                                .where(tarefa.fechada.eq(false)
                                        .and(tarefa.classificacao.id.eq(1L))),

                        JPAExpressions.select(tarefa.id.count())
                                .from(tarefa)
                                .where(tarefa.fechada.eq(false)
                                        .and(tarefa.classificacao.id.eq(2L))),

                        JPAExpressions.select(tarefa.id.count())
                                .from(tarefa)
                                .where(tarefa.fechada.eq(false)
                                        .and(tarefa.classificacao.id.eq(3L)))
                ))
                .from(tarefa)
                .where(tarefa.fechada.eq(false))
                .fetch().stream().toList().get(0);
    }

    public List<DadoDashboardPorProjetoResponse> contarTarefasPorProjeto() {
        QTarefa tarefa = QTarefa.tarefa;

        return queryFactory
                .select(Projections.constructor(DadoDashboardPorProjetoResponse.class,
                        tarefa.projeto.id.count().as("valorTotalTarefas"),
                        tarefa.projeto.nome.as("projetoNome")
                ))
                .from(tarefa)
                .where(tarefa.fechada.eq(false))
                .groupBy(tarefa.projeto.id, tarefa.projeto.nome)
                .fetch();
    }

    @Override
    public List<DadoDashboardPorAnoResponse> contarTarefasPorAno() {
        QTarefa tarefa = QTarefa.tarefa;

        int currentYear = LocalDate.now().getYear();

        return queryFactory
                .select(Projections.constructor(DadoDashboardPorAnoResponse.class,
                        tarefa.dataCriacao.year().as("year"),
                        tarefa.dataCriacao.month().as("month"),
                        new CaseBuilder()
                                .when(tarefa.classificacao.id.eq(1L)).then(1)
                                .otherwise(0).sum().as("valorIncidente"),
                        new CaseBuilder()
                                .when(tarefa.classificacao.id.eq(2L)).then(1)
                                .otherwise(0).sum().as("valorProblema"),
                        new CaseBuilder()
                                .when(tarefa.classificacao.id.eq(3L)).then(1)
                                .otherwise(0).sum().as("valorMudanca")
                ))
                .from(tarefa)
                .where(tarefa.fechada.eq(false)
                        .and(tarefa.dataCriacao.year().eq(currentYear)))
                .groupBy(tarefa.dataCriacao.year(), tarefa.dataCriacao.month())
                .orderBy(tarefa.dataCriacao.year().asc(), tarefa.dataCriacao.month().asc())
                .fetch();
    }
}
