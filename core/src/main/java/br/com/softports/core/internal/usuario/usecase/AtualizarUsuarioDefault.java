package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.*;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AtualizarUsuarioDefault implements AtualizarUsuario {

    private final AtualizarUsuarioKeycloak atualizarUsuarioKeycloak;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioToUsuarioResponse usuarioToUsuarioResponse;

    @Override
    public UsuarioResponse executar(UUID id, String nome, String email, String username) {
        BooleanBuilder filtro = new BooleanBuilder(UsuarioExpressions.keycloakId(id));
        Usuario usuario = usuarioRepository.buscar(filtro).orElseThrow();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuarioRepository.salvar(usuario);
        atualizarUsuarioKeycloak.executar(id, nome, email, username);
        return usuarioToUsuarioResponse.executar(usuario);
    }
}