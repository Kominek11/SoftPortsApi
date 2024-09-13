package br.com.softports.application.infrastructure.repository.softports.audited;

import br.com.softports.application.infrastructure.repository.softports.RepositorioDefault;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa_aud.repository.TarefaAudRepository;
import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.common.entity.audited.QTarefaAud;
import br.com.softports.core.internal.common.entity.audited.TarefaAud;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TarefaAudRepositoryDefault extends RepositorioDefault<TarefaAud, Long> implements TarefaAudRepository {

    public TarefaAudRepositoryDefault(EntityManager entityManager) {

        super(TarefaAud.class, entityManager, QTarefaAud.tarefaAud);
    }
}
