package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.subclassificacao.repository.SubClassificacaoRepository;
import br.com.softports.core.internal.common.entity.Classificacao;
import br.com.softports.core.internal.common.entity.QClassificacao;
import br.com.softports.core.internal.common.entity.QSubClassificacao;
import br.com.softports.core.internal.common.entity.SubClassificacao;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class SubClassificacaoRepositoryDefault extends RepositorioDefault<SubClassificacao, Long> implements SubClassificacaoRepository {

    public SubClassificacaoRepositoryDefault(EntityManager entityManager) {
        super(SubClassificacao.class, entityManager, QSubClassificacao.subClassificacao);
    }
}
