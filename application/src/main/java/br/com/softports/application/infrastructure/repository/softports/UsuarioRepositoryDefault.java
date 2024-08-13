package br.com.softports.application.infrastructure.repository.softports;

import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.QProjeto;
import br.com.softports.core.internal.common.entity.QUsuario;
import br.com.softports.core.internal.common.entity.Usuario;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryDefault extends RepositorioDefault<Usuario, Long> implements UsuarioRepository {

    public UsuarioRepositoryDefault(EntityManager entityManager) {
        super(Usuario.class, entityManager, QUsuario.usuario);
    }
}
