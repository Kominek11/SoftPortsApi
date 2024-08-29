package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.prioridade.repository.PrioridadeRepository;
import br.com.softports.core.internal.common.entity.Classificacao;
import br.com.softports.core.internal.common.entity.Prioridade;
import br.com.softports.core.internal.common.entity.QClassificacao;
import br.com.softports.core.internal.common.entity.QPrioridade;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class PrioridadeRepositoryDefault extends RepositorioDefault<Prioridade, Long> implements PrioridadeRepository {

    public PrioridadeRepositoryDefault(EntityManager entityManager) {
        super(Prioridade.class, entityManager, QPrioridade.prioridade);
    }
}
