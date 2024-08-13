package br.com.softports.core.internal.usuario.expression;

import br.com.softports.core.internal.common.entity.QTarefa;
import br.com.softports.core.internal.common.entity.QUsuario;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class UsuarioExpressions {

    private static final QUsuario USUARIO = QUsuario.usuario;

    public static BooleanExpression todos() {
        return null;
    }

    public static BooleanExpression id(Long usuarioId) {
        return Objects.nonNull(usuarioId) ? USUARIO.id.eq(usuarioId) : null;
    }
}
