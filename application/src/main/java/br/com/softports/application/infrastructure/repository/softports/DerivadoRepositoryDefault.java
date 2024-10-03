package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.QDerivado;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class DerivadoRepositoryDefault extends RepositorioDefault<Derivado, Long> implements DerivadoRepository {

    public DerivadoRepositoryDefault(EntityManager entityManager) {
        super(Derivado.class, entityManager, QDerivado.derivado);
    }
}
