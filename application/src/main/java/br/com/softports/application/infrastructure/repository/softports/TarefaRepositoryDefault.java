package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.QOrganizacao;
import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.Tarefa;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
