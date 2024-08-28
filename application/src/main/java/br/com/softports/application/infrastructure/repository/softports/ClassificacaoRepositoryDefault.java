package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.internal.common.entity.Classificacao;
import br.com.softports.core.internal.common.entity.Comentario;
import br.com.softports.core.internal.common.entity.QClassificacao;
import br.com.softports.core.internal.common.entity.QComentario;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClassificacaoRepositoryDefault extends RepositorioDefault<Classificacao, Long> implements ClassificacaoRepository {

    public ClassificacaoRepositoryDefault(EntityManager entityManager) {
        super(Classificacao.class, entityManager, QClassificacao.classificacao);
    }
}
