package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.QOrganizacao;
import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.Tarefa;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class TarefaRepositoryDefault extends RepositorioDefault<Tarefa, Long> implements TarefaRepository {

    public TarefaRepositoryDefault(EntityManager entityManager) {
        super(Tarefa.class, entityManager, QTarefa.tarefa);
    }
}
