package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.internal.common.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SolicitacaoRepositoryDefault extends RepositorioDefault<Solicitacao, Long>
        implements SolicitacaoRepository {

    public SolicitacaoRepositoryDefault(EntityManager entityManager) {
        super(Solicitacao.class, entityManager, QSolicitacao.solicitacao);
    }

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Optional<Long> findMaxPosicao(Long status) {
        QSolicitacao solicitacao = QSolicitacao.solicitacao;
        Long maxPosicao = queryFactory
                .select(solicitacao.posicao.max())
                .from(solicitacao)
                .where(solicitacao.status.eq(status))
                .fetchOne();
        return Optional.ofNullable(maxPosicao);
    }
}
