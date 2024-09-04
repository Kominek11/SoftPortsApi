package br.com.softports.core.internal.usuario.expression;

import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.QUsuario;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;
import java.util.UUID;

public class UsuarioExpressions {

    private static final QUsuario USUARIO = QUsuario.usuario;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long usuarioId) {
        return Objects.nonNull(usuarioId) ? USUARIO.id.eq(usuarioId) : null;
    }

    public static BooleanExpression projetoId(Long projetoId) {
        return Objects.nonNull(projetoId) ? USUARIO.projetos.any().id.eq(projetoId) : null;
    }

    public static BooleanExpression nome(String nome) {
        return Objects.nonNull(nome) ? USUARIO.nome.likeIgnoreCase("%" + nome + "%") : null;
    }

    public static BooleanExpression keycloakId(UUID keycloakId) {
        return Objects.nonNull(keycloakId) ? USUARIO.keycloakId.eq(keycloakId) : null;
    }
}
