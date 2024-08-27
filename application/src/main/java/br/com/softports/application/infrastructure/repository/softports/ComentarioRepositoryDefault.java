package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.internal.common.entity.Comentario;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.QComentario;
import br.com.softports.core.internal.common.entity.QProjeto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioRepositoryDefault extends RepositorioDefault<Comentario, Long> implements ComentarioRepository {

    public ComentarioRepositoryDefault(EntityManager entityManager) {
        super(Comentario.class, entityManager, QComentario.comentario);
    }
}
