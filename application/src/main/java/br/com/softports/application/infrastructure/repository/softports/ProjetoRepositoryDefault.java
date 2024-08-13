package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.QProjeto;
import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.Tarefa;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ProjetoRepositoryDefault extends RepositorioDefault<Projeto, Long> implements ProjetoRepository {

    public ProjetoRepositoryDefault(EntityManager entityManager) {
        super(Projeto.class, entityManager, QProjeto.projeto);
    }
}
