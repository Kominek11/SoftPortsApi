package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.QOrganizacao;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizacaoRepositoryDefault extends RepositorioDefault<Organizacao, Long> implements OrganizacaoRepository {

    public OrganizacaoRepositoryDefault(EntityManager entityManager) {
        super(Organizacao.class, entityManager, QOrganizacao.organizacao);
    }
}
