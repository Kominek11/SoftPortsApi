//package br.com.softports.core.internal.common.expression;
//
//import br.com.softports.core.internal.common.entity.QUsuario;
//import com.querydsl.core.types.dsl.BooleanExpression;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//public class UsuarioExpressions {
//
//    private final static QUsuario USUARIO = QUsuario.usuario;
//    public static BooleanExpression usuarioId(Long usuarioId) {
//        return USUARIO.id.eq(usuarioId);
//    }
//
//    public static BooleanExpression usuarioList(List<Long> usuarioIdList) {
//        return USUARIO.id.in(usuarioIdList);
//    }
//
//    public static BooleanExpression emailUsuario(String emailUsuario) {
//        return USUARIO.email.eq(emailUsuario);
//    }
//    public static BooleanExpression idUsuarioKeycloak(UUID idUsuarioKeycloak) { return USUARIO.idKeycloak.eq(idUsuarioKeycloak);}
//
//    public static BooleanExpression role(UsuarioRole role) { return Objects.nonNull(role) ? USUARIO.role.eq(role) : null;}
//
//}
