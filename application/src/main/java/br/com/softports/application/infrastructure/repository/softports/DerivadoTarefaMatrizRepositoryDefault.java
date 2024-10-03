package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado_tarefa_matriz.repository.DerivadoTarefaMatrizRepository;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.DerivadoTarefaMatriz;
import br.com.softports.core.internal.common.entity.QDerivado;
import br.com.softports.core.internal.common.entity.QDerivadoTarefaMatriz;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class DerivadoTarefaMatrizRepositoryDefault extends RepositorioDefault<DerivadoTarefaMatriz, Long> implements
        DerivadoTarefaMatrizRepository {

    public DerivadoTarefaMatrizRepositoryDefault(EntityManager entityManager) {
        super(DerivadoTarefaMatriz.class, entityManager, QDerivadoTarefaMatriz.derivadoTarefaMatriz);
    }
}
