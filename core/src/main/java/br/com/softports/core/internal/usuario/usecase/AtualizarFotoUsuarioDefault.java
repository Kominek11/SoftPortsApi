package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.AtualizarFotoUsuario;
import br.com.softports.core.api.usuario.usecase.AtualizarUsuario;
import br.com.softports.core.api.usuario.usecase.AtualizarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AtualizarFotoUsuarioDefault implements AtualizarFotoUsuario {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioToUsuarioResponse usuarioToUsuarioResponse;

    @Override
    public UsuarioResponse executar(UUID id,byte[] foto) {
        BooleanBuilder filtro = new BooleanBuilder(UsuarioExpressions.keycloakId(id));
        Usuario usuario = usuarioRepository.buscar(filtro).orElseThrow();
        usuario.setFoto(foto);
        usuarioRepository.salvar(usuario);
        return usuarioToUsuarioResponse.executar(usuario);
    }
}